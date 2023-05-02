import com.alibaba.fastjson.JSON;
import com.zhangtao.springcloud.entities.CommonResult;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 1. @ClassDescription:
 * 2. @author: ZhangTao
 * 3. @date: 2023年03月13日 19:36
 */
@SpringBootTest
public class ElasticSearchTeat {

    @Resource
    //@Qualifier("restHighLevelClient")  //别名指定
    private RestHighLevelClient client;

    @Test  //测试索引创建  Request
    public void contextTest() throws IOException {
        //创建请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        //执行请求  获取响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }
    //测试索引是否存在
    @Test
    void testExisIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index");
        boolean exists = client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //测试删除索引
    void testDelectIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse);
    }
    //创建文档
    @Test
    void createAddDocument() throws IOException {
        CommonResult<Object> result = new CommonResult<>(222, "success", null);
        IndexRequest request = new IndexRequest("kuang_index");
        //设置规则  put /kuang_index/_doc/1
        request.id("1");request.timeout(TimeValue.timeValueSeconds(1));
        //将我们的数据放入请求
        IndexRequest source = request.source(JSON.toJSONString(result), XContentType.JSON);
        //客户端发送请求
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        System.out.println(index.status());
    }
    //判断是否存在  get /index/doc/1
    @Test
    void testIsExists() throws IOException {
        GetRequest request = new GetRequest("kuang_index", "1");
        //不获取返回的 _source 上下文
        GetRequest getRequest = request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //获取文档信息
    @Test
    void testGetDoucment() throws IOException {
        GetRequest request = new GetRequest("kuang_index", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString()); //文档内容
        System.out.println(request); //返回全部内容 和命令执行是一样的
    }
    //修改文档信息
    @Test
    void testChangeDoucment() throws IOException {
        UpdateRequest request = new UpdateRequest("kuang_index", "1");
        request.timeout("1s");

        CommonResult<Object> fail = new CommonResult<>(222, "fail", null);
        request.doc(JSON.toJSONString(fail),XContentType.JSON);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.status()); //修改是否成功
    }
    //删除文档
    @Test
    void testDelDoucment() throws IOException {
        DeleteRequest request = new DeleteRequest("kuang_index", "1");
        request.timeout("1s");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status()); //修改是否成功
    }
    //批量插入数据  项目中一般都是批量的
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<CommonResult> commList = new ArrayList<>();
        commList.add(new CommonResult(111,"sucess",null));
        commList.add(new CommonResult(222,"faile",null));
        commList.add(new CommonResult(333,"up",null));
        //批处理请求
        for (int i = 0; i < commList.size(); i++) {
            //批量更新和批量删除 就在这里修改就行  不设置id 就是随机的
            bulkRequest.add(new IndexRequest("kuang_index1").id(""+(i+1)).source(
                    JSON.toJSONString(commList.get(i)),XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); //是否失败
    }

    //查询
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kuang_index");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置高亮
        sourceBuilder.highlighter();
        //查询条件，我们可以使用 QueryBuilders 工具来实现
        //QueryBuilders.termQuery 精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "sucess");
        sourceBuilder.query(termQueryBuilder);
        //QueryBuilders.matchAllQuery() 匹配所有
        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        sourceBuilder.query(matchAllQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }

    }

}
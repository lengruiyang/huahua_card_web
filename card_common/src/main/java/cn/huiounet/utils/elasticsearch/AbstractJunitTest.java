package cn.huiounet.utils.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AbstractJunitTest {
    protected Client client;

    /**
     * 获取一个客户端
     */
    @SuppressWarnings("resource")
    @Before
    public  void getClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "lry").build();

        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("39.99.136.115"), 9300);

        client = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
    }

    /**
     * 关闭连接
     */
    @After
    public void close() {
        client.close();
    }
}

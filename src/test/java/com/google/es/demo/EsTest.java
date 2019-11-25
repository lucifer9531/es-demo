package com.google.es.demo;

import com.google.es.pojo.Item;
import com.google.es.repository.ItemPepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemPepository itemPepository;

    @Test
    /**
     * 创建索引
     */
    public void testCreate() {
        // 创建索引库
        elasticsearchTemplate.createIndex(Item.class);

        // 映射关系
        elasticsearchTemplate.putMapping(Item.class);

    }

    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemPepository.saveAll(list);
    }

    @Test
    public void testFind() {
        Iterable<Item> all = itemPepository.findAll();
        for (Item item : all) {
            System.out.println("item = " + item);
        }
    }

    @Test
    public void testFindBy() {
        List<Item> byPriceBetween = itemPepository.findByPriceBetween(2000d, 4000d);
        for (Item item : byPriceBetween) {
            System.out.println("item = " + item);
        }
    }
}

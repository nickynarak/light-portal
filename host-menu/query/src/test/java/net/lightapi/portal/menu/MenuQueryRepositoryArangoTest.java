package net.lightapi.portal.menu;

import com.arangodb.ArangoDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.config.Config;
import com.networknt.service.SingletonServiceFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuQueryRepositoryArangoTest {
    static ArangoDB arangoDB;
    static MenuRepository menuQueryRepository = SingletonServiceFactory.getBean(MenuRepository.class);
    ObjectMapper mapper = Config.getInstance().getMapper();
    @BeforeClass
    public static void setUp() {
        arangoDB = (ArangoDB)menuQueryRepository.getDataSource();
        // add testing data
    }

    @AfterClass
    public static void tearDown() {
        // remove testing data
    }

    @Test
    public void testCreateMenuItem() throws Exception {
        // clean up all vertexes if exist.
        menuQueryRepository.removeMenu("1");
        menuQueryRepository.removeMenuItem("11");
        menuQueryRepository.removeMenuItem("12");
        menuQueryRepository.removeMenuItem("13");
        menuQueryRepository.removeMenuItem("1");
        // at this moment, the edge collect should be removed. verify it.


        String s11 = "{\"menuItemId\":\"11\",\"label\":\"Host Admin\",\"route\":\"/admin/hostAdmin\",\"roles\":[\"admin\",\"owner\"]}";
        String s12 = "{\"menuItemId\":\"12\",\"label\":\"Rule Admin\",\"route\":\"/admin/ruleAdmin\",\"roles\":[\"ruleAdmin\",\"admin\",\"owner\"]}";
        String s13 = "{\"menuItemId\":\"13\",\"label\":\"Form Admin\",\"route\":\"/admin/formAdmin\",\"roles\":[\"formAdmin\",\"admin\",\"owner\"]}";
        String s1 = "{\"menuItemId\":\"1\",\"label\":\"admin\",\"route\":\"/admin\",\"roles\":[\"admin\",\"owner\"],\"contains\":[\"11\",\"12\",\"13\"]}";
        String s2 = "{\"menuItemId\":\"2\",\"label\":\"login\",\"route\":\"/login\",\"roles\":[\"user\"]}";
        String s3 = "{\"menuItemId\":\"3\",\"label\":\"logout\",\"route\":\"/logout\",\"roles\":[\"user\"]}";


        menuQueryRepository.createMenuItem("e11", s11);
        menuQueryRepository.createMenuItem("e12", s12);
        menuQueryRepository.createMenuItem("e13", s13);
        menuQueryRepository.createMenuItem("e1", s1);
        menuQueryRepository.createMenuItem("e2", s2);
        menuQueryRepository.createMenuItem("e3", s3);


        String site1 = "{\"host\":\"example.com\",\"description\":\"example site\",\"contains\":[\"1\",\"2\",\"3\"]}";
        menuQueryRepository.createMenu("e1", site1);

    }

    @Test
    public void testSave() {
        /*
        Menu menu = new Menu();
        menu.setHost("networknt.com");
        menu.setDescription("Menu for networknt.com");
        //menuQueryRepository.save(menu.getHost(), menu);
        */
    }
}

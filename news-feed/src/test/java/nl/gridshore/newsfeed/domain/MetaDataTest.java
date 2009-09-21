package nl.gridshore.newsfeed.domain;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;

/**
 * Test case for the {@link MetaData} class.
 *
 * @author Jettro Coenradie
 */
public class MetaDataTest {
    private static final String TAG_TECHNICAL = "technical";
    private static final String TAG_CACHING = "caching";

    @Test
    public void testTags() {
        MetaData metaData = createMetaData();
        metaData.tag(TAG_TECHNICAL);

        String[] tags = metaData.tags();
        assertEquals("number of items in tags is wrong",1,tags.length);
        assertEquals("The first item in tags is wrong", TAG_TECHNICAL,tags[0]);

        metaData.tag(TAG_CACHING);
        String[] twoTags = metaData.tags();
        assertEquals("objects returned by tags should be different",1,tags.length);
        assertEquals("Number of items after adding the second tag is wrong",2,twoTags.length);
        assertEquals("The first item in tags is wrong", TAG_TECHNICAL,twoTags[0]);
        assertEquals("The first item in tags is wrong", TAG_CACHING,twoTags[1]);
    }

    @Test
    public void testNoTags() {
        MetaData metaData = createMetaData();
        String[] tags = metaData.tags();
        assertNotNull("Should return an empty string array if no tags are available",tags);
        assertEquals("Length of empty string array is not correct",0,tags.length);
    }

    @Test
    public void testNullTag() {
        MetaData metaData = createMetaData();
        metaData.tag(null);
        assertNotNull("Should return an empty string array if no tags are available",metaData.tags());
        assertEquals("meta data should still have no tags",0,metaData.tags().length);
        metaData.tag(TAG_TECHNICAL);
        metaData.tag(null);
        assertEquals("Number of items after adding the second tag is wrong",1,metaData.tags().length);
        assertEquals("The first item in tags is wrong", TAG_TECHNICAL,metaData.tags()[0]);

    }

    private MetaData createMetaData() {
        return new MetaData("jettro", new Date());
    }
}

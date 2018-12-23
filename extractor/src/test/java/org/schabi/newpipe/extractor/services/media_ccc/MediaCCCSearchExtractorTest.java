package org.schabi.newpipe.extractor.services.media_ccc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.schabi.newpipe.Downloader;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.search.SearchExtractor;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCSearchExtractor;
import org.schabi.newpipe.extractor.utils.Localization;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.schabi.newpipe.extractor.ServiceList.MediaCCC;

/**
 * Test for {@link MediaCCCSearchExtractor}
 */
public class MediaCCCSearchExtractorTest {
    private static SearchExtractor extractor;
    private static ListExtractor.InfoItemsPage<InfoItem> itemsPage;

    @BeforeClass
    public static void setUpClass() throws Exception {
        NewPipe.init(Downloader.getInstance(), new Localization("GB", "en"));
        extractor =  MediaCCC.getSearchExtractor("source");
        extractor.fetchPage();
        itemsPage = extractor.getInitialPage();
    }

    @Test
    public void testCount() throws Exception {
        assertTrue(Integer.toString(itemsPage.getItems().size()),
                itemsPage.getItems().size() >= 25);
    }

    @Test
    public void testServiceId() throws Exception {
        assertEquals(2, extractor.getServiceId());
    }

    @Test
    public void testName() throws Exception {
        assertEquals("Hardening Open Source Development", itemsPage.getItems().get(0).getName());
    }

    @Test
    public void testUrl() throws Exception {
        assertEquals("https://api.media.ccc.de/public/events/9e774be1-eb68-4ccc-94bd-a65f9abd752d",
                itemsPage.getItems().get(0).getUrl());
    }

    @Test
    public void testThumbnailUrl() throws Exception {
        assertEquals("https://static.media.ccc.de/media/congress/2017/9249-hd.jpg",
                itemsPage.getItems().get(0).getThumbnailUrl());
    }
}

package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.mobileads.test.support.SdkTestRunner;
import com.mopub.mobileads.test.support.TestCustomEventBannerAdapterFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SdkTestRunner.class)
public class MoPubViewTest {
    private MoPubView subject;
    private Map<String,String> paramsMap = new HashMap<String, String>();
    private CustomEventBannerAdapter customEventBannerAdapter;

    @Before
    public void setup() {
        subject = new MoPubView(new Activity());
        customEventBannerAdapter = TestCustomEventBannerAdapterFactory.getSingletonMock();
    }

    @Test
    public void loadCustomEvent_shouldInitializeCustomEventBannerAdapter() throws Exception {
        paramsMap.put(AdFetcher.CUSTOM_EVENT_NAME_HEADER, "name");
        paramsMap.put(AdFetcher.CUSTOM_EVENT_DATA_HEADER, "data");
        paramsMap.put(AdFetcher.CUSTOM_EVENT_HTML_DATA, "html");
        TestCustomEventBannerAdapterFactory.reset();
        subject.loadCustomEvent(paramsMap);

        assertThat(TestCustomEventBannerAdapterFactory.getLatestMoPubView()).isEqualTo(subject);
        assertThat(TestCustomEventBannerAdapterFactory.getLatestClassName()).isEqualTo("name");
        assertThat(TestCustomEventBannerAdapterFactory.getLatestClassData()).isEqualTo("data");

        verify(customEventBannerAdapter).loadAd();
    }
}

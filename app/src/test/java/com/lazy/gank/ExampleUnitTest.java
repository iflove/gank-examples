package com.lazy.gank;

import com.lazy.gank.rx.RxBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testBus() throws InterruptedException {

		CompositeSubscription subscription = new CompositeSubscription();
		subscription.add(RxBus.getDefault().toObserverable(OneEvent.class).subscribe(new Action1<OneEvent>() {
			@Override
			public void call(OneEvent oneEvent) {
				System.out.println(oneEvent.msg);
			}
		}));
		Thread.sleep(100);
		RxBus.getDefault().post(new OneEvent("Hello"));
	}
}

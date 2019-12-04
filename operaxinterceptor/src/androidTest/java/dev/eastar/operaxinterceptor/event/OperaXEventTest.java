package dev.eastar.operaxinterceptor.event;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OperaXEventTest {
    @Test
    public void sendTest() {
        OperaXEventObservable.notify(OperaXEvents.EXIT);
        OperaXEventObservable.notify(new EventLogin("dfsdfs"));
        OperaXEventObservable.notify(new EventLogin());
    }
}

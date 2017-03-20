package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensor sensor;
    TrainController mockTC;
    TrainUser mockUser;

    @Before
    public void before() {

        mockTC = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockTC,mockUser);
    }

    @Test
    public void alarm1() {
       sensor.overrideSpeedLimit(600);
        verify(mockUser, times(1)).setAlarmState(true);
    }
    @Test
    public void alarm2() {
        sensor.overrideSpeedLimit(-20);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void alarm3() {
        when(mockTC.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(1);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void alarm4() {
        when(mockTC.getReferenceSpeed()).thenReturn(200);
        sensor.overrideSpeedLimit(180);

        verify(mockUser, times(0)).setAlarmState(true);

    }
}

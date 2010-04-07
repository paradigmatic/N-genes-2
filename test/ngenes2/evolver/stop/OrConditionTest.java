package ngenes2.evolver.stop;


import ngenes2.population.Population;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class OrConditionTest {

    @Test
    public void testOr() {
        StopCondition condFalse = mock(StopCondition.class);
        when( condFalse.shouldStop(anyInt(), any(Population.class)) ).thenReturn(false);
        StopCondition condTrue = mock(StopCondition.class);
        when( condTrue.shouldStop(anyInt(), any(Population.class)) ).thenReturn(true);
        assertEquals(false, new OrCondition(condFalse, condFalse).shouldStop(0, null) );
        assertEquals(true, new OrCondition(condFalse, condTrue).shouldStop(0, null) );
        assertEquals(true, new OrCondition(condTrue, condFalse).shouldStop(0, null) );
        assertEquals(true, new OrCondition(condTrue, condTrue).shouldStop(0, null) );
    }


}
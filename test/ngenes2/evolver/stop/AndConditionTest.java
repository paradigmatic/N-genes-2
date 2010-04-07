package ngenes2.evolver.stop;


import ngenes2.population.Population;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class AndConditionTest {

    @Test
    public void testAnd() {
        StopCondition condFalse = mock(StopCondition.class);
        when( condFalse.shouldStop(anyInt(), any(Population.class)) ).thenReturn(false);
        StopCondition condTrue = mock(StopCondition.class);
        when( condTrue.shouldStop(anyInt(), any(Population.class)) ).thenReturn(true);
        assertEquals(false, new AndCondition(condFalse, condFalse).shouldStop(0, null) );
        assertEquals(false, new AndCondition(condFalse, condTrue).shouldStop(0, null) );
        assertEquals(false, new AndCondition(condTrue, condFalse).shouldStop(0, null) );
        assertEquals(true, new AndCondition(condTrue, condTrue).shouldStop(0, null) );
    }

}
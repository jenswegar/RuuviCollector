package fi.tkgwf.ruuvi.strategy.impl;

import fi.tkgwf.ruuvi.bean.RuuviMeasurement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultDiscardingWithMotionSensitivityStrategyTest {
    @Test
    void testMotionSensitivity() {
        final DefaultDiscardingWithMotionSensitivityStrategy strategy = new DefaultDiscardingWithMotionSensitivityStrategy();

        final RuuviMeasurement withinInterval = new RuuviMeasurement();
        withinInterval.accelerationX = 0.98d;

        final RuuviMeasurement belowLower = new RuuviMeasurement();
        belowLower.accelerationX = 0.5d;


        final RuuviMeasurement aboveUpper = new RuuviMeasurement();
        aboveUpper.accelerationX = 2.5d;

        assertTrue(strategy.apply(withinInterval).isPresent()); // Because no previous measurements
        assertFalse(strategy.apply(withinInterval).isPresent()); // Because of recent measurement
        assertFalse(strategy.apply(withinInterval).isPresent()); // Because of recent measurement

        assertTrue(strategy.apply(belowLower).isPresent()); // Because it's below the lower limit
        assertTrue(strategy.apply(belowLower).isPresent()); // Because it's not changing but happens right after a change
        assertFalse(strategy.apply(belowLower).isPresent()); // Because it's not changing. It's low, but not changing.

        assertTrue(strategy.apply(withinInterval).isPresent()); // Because it's changing.

        assertTrue(strategy.apply(aboveUpper).isPresent()); // Because it's changing.
        assertTrue(strategy.apply(aboveUpper).isPresent()); // Because not changing but happens right after a change
        assertFalse(strategy.apply(aboveUpper).isPresent()); // Because it's not changing. It's high, but not changing
    }
}

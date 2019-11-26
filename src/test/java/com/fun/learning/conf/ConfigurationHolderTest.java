package com.fun.learning.conf;

import org.junit.Test;

import com.fun.learning.conf.Configuration;
import com.fun.learning.conf.ConfigurationHolder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Configuration holder tests.
 *
 * @author Igor Bolic
 */
public class ConfigurationHolderTest {

    @Test
    public void thatConfigurationValuesAreLoaded() {
        final Configuration configuration = ConfigurationHolder.INSTANCE.configuration();

        assertThat(configuration, notNullValue());
        assertThat(configuration.getDatasource(), notNullValue());
        assertThat(configuration.getDatasource().getDriverClassName(), is("org.h2.Driver"));
        assertThat(configuration.getDatasource().getUrl(), is("jdbc:h2:mem:test"));
        assertThat(configuration.getDatasource().getUsername(), is("test-username"));
        assertThat(configuration.getDatasource().getPassword(), is("test-password"));
    }

}
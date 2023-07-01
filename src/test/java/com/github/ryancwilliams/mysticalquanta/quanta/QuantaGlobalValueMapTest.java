package com.github.ryancwilliams.mysticalquanta.quanta;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuantaGlobalValueMapTest {

	QuantaGlobalValueMap valueMap;

	@BeforeEach
	void setUp() {
		valueMap = new QuantaGlobalValueMap();
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getQuantaValueMapSizeEmpty() {
		assertEquals(0, valueMap.getQuantaValueMapSize());
	}

}
package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.almansa.app.domain.value.Money;
import org.junit.Test;

public class ValueObjectTest {
	@Test
	public void testBigDecimalImmutable() {
		BigDecimal val1 = new BigDecimal("20000");
		BigDecimal val2 = new BigDecimal("20000");

		boolean isEqual = val1.equals(val2);
		boolean isReferenceEqual = val1 == val2;

		assertEquals(true, isEqual);
		assertEquals(false, isReferenceEqual);
	}

	@Test
	public void testMoneyImmutable() {
		Money m1 = new Money(20000);
		Money m2 = new Money(20000);

		boolean isEqual = m1.equals(m2);
		boolean isReferenceEqual = m1 == m2;

		assertEquals(true, isEqual);
		assertEquals(false, isReferenceEqual);
	}
}

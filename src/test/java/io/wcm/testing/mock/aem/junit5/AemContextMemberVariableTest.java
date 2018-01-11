/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2018 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.testing.mock.aem.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.resourceresolver.MockResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Test with {@link AemContext} which uses by default {@link ResourceResolverMockAemContext}.
 */
@ExtendWith(AemContextExtension.class)
@Tag("junit5")
@Disabled // TODO: this does not work - constructor injection has no relation to test method - also lifecyle=PER_METHOD - why?
class AemContextMemberVariableTest {

  final AemContext context;

  AemContextMemberVariableTest(AemContext context) {
    this.context = context;
  }

  @BeforeEach
  void setUp() {
    assertTrue(context instanceof ResourceResolverMockAemContext);
    assertTrue(context.resourceResolver() instanceof MockResourceResolver);

    context.create().resource("/content/test",
        "prop1", "value1");
  }

  @Test
  void testResource() {
    Resource resource = context.resourceResolver().getResource("/content/test");
    assertEquals("value1", resource.getValueMap().get("prop1"));
  }

}

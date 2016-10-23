/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lazy.gank.internal.di.components;

import com.lazy.gank.internal.di.modules.ActivityModule;
import com.lazy.gank.internal.di.rules.IActivityRule;
import com.lazy.gank.internal.di.scope.ActivityScope;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link ActivityScope}
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class},
		modules = {ActivityModule.class})
public interface ActivityComponent extends IActivityRule {
	//Exposed to sub-graphs.

	FragmentComponent getFragmentComponent();

}

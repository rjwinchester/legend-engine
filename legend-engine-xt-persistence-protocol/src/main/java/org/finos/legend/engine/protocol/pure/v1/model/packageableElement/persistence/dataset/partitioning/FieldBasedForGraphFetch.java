// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.protocol.pure.v1.model.packageableElement.persistence.dataset.partitioning;

import org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.raw.classInstance.path.Path;

import java.util.List;

public class FieldBasedForGraphFetch extends FieldBased
{
    public List<Path> partitionFieldPaths;

    @Override
    public <T> T accept(FieldBasedVisitor<T> visitor)
    {
        return visitor.visitFieldBasedForGraphFetch(this);
    }
}

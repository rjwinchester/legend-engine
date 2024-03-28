// Copyright 2022 Goldman Sachs
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

package org.finos.legend.engine.plan.execution.stores.relational.test.semiStructured;

import org.junit.Assert;
import org.junit.Test;

public class TestSnowflakeSemiStructuredParseJsonMapping extends AbstractTestSnowflakeSemiStructured
{
    private static final String snowflakeMapping = "parseJson::mapping::SnowflakeMapping";
    private static final String snowflakeRuntime = "parseJson::runtime::SnowflakeRuntime";

    @Test
    public void testParseJsonInMapping()
    {
        String snowflakePlan = this.buildExecutionPlanString("parseJson::parseJsonInMapping__TabularDataSet_1_", snowflakeMapping, snowflakeRuntime);
        String snowflakeExpected =
                "    Relational\n" +
                "    (\n" +
                "      type = TDS[(First Name, String, VARCHAR(100), \"\"), (Firm Name, String, VARCHAR(65536), \"\"), (Manager Firm Legal Name, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name Dup1, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name Dup2, String, VARCHAR(65536), \"\")]\n" +
                "      resultColumns = [(\"First Name\", VARCHAR(100)), (\"Firm Name\", \"\"), (\"Manager Firm Legal Name\", \"\"), (\"Manager Manager Firm Legal Name\", \"\"), (\"Manager Manager Firm Legal Name Dup1\", \"\"), (\"Manager Manager Firm Legal Name Dup2\", \"\")]\n" +
                "      sql = select \"root\".FIRSTNAME as \"First Name\", parse_json(\"root\".FIRM_DETAILS)['legalName']::varchar as \"Firm Name\", parse_json(\"person_table_varchar_1\".FIRM_DETAILS)['legalName']::varchar as \"Manager Firm Legal Name\", parse_json(\"person_table_varchar_2\".FIRM_DETAILS)['legalName']::varchar as \"Manager Manager Firm Legal Name\", parse_json(\"person_table_varchar_3\".FIRM_DETAILS)['legalName']::varchar as \"Manager Manager Firm Legal Name Dup1\", parse_json(\"person_table_varchar_5\".FIRM_DETAILS)['legalName']::varchar as \"Manager Manager Firm Legal Name Dup2\" from PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"root\" left outer join PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"person_table_varchar_1\" on (\"root\".MANAGERID = \"person_table_varchar_1\".ID) left outer join PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"person_table_varchar_2\" on (\"person_table_varchar_1\".MANAGERID = \"person_table_varchar_2\".ID) left outer join PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"person_table_varchar_3\" on (\"person_table_varchar_1\".MANAGERID = \"person_table_varchar_3\".ID) left outer join PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"person_table_varchar_4\" on (\"root\".MANAGERID = \"person_table_varchar_4\".ID) left outer join PERSON_SCHEMA.PERSON_TABLE_VARCHAR as \"person_table_varchar_5\" on (\"person_table_varchar_4\".MANAGERID = \"person_table_varchar_5\".ID)\n" +
                "      connection = RelationalDatabaseConnection(type = \"Snowflake\")\n" +
                "    )\n";
        String TDSType = "  type = TDS[(First Name, String, VARCHAR(100), \"\"), (Firm Name, String, VARCHAR(65536), \"\"), (Manager Firm Legal Name, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name Dup1, String, VARCHAR(65536), \"\"), (Manager Manager Firm Legal Name Dup2, String, VARCHAR(65536), \"\")]\n";
        Assert.assertEquals(wrapPreAndFinallyExecutionSqlQuery(TDSType, snowflakeExpected), snowflakePlan);
    }

    public String modelResourcePath()
    {
        return "/org/finos/legend/engine/plan/execution/stores/relational/test/semiStructured/semiStructuredParseJsonMapping.pure";
    }
}

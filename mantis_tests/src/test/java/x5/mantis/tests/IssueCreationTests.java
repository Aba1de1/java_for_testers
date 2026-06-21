package x5.mantis.tests;

import org.junit.jupiter.api.Test;
import x5.mantis.common.Common;
import x5.mantis.model.IssueData;

public class IssueCreationTests extends TestBase{

    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(Common.randomString(10))
                .withDescription(Common.randomString(50))
                .withProject(1L));
    }

}
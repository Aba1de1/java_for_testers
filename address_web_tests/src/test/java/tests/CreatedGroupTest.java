package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class CreatedGroupTest extends TestBase {

    @Test
    public void canCreatedGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void createdGroupWithoutName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void createdGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}

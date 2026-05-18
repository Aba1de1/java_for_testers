package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveGroupTest extends TestBase {

    @Test
    public void CanRemoveGroup() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int NewGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, NewGroupCount);
    }

    @Test
    void canRemoveAllGroupsAtOnce(){
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}

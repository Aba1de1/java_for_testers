package tests;

import common.Common;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class ModificationGroupTest extends TestBase {
    @Test
    void canModifyGroup() {
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(Common.randomString(18));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

}

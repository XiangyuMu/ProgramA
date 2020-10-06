package TestCase;

import reduceExample.Element;
import reduceExample.ElemwntList;
import testList.TestInput;

import java.util.*;

public class ToolBox {
    public ElemwntList el;
    public List<Integer> relatedList = new ArrayList<Integer>();
    public List<Integer> changedList = new ArrayList<Integer>();
    private List<ElemwntList> elementListList = new ArrayList<ElemwntList>();

    public static Stack<Integer> stack = new Stack<Integer>();
    public ElemwntList getEl() {
        return el;
    }

    public void setEl(ElemwntList el) {
        this.el = el;
    }

    public List<Integer> getRelatedList() {
        return relatedList;
    }

    public void setRelatedList(List<Integer> relatedList) {
        this.relatedList = relatedList;
    }

    public List<ElemwntList> getElementListList() {
        return elementListList;
    }

    public void setElementListList(List<ElemwntList> elementListList) {
        this.elementListList = elementListList;
    }

    public void SingleItem(int num){
        TestInput ti = new TestInput();
        el = ti.createTestCase_Single("ProgramTest/src/main/java/TestCase/case"+num+".txt", "String", "String");
        if (relatedList.size()==1){
            for (int i = 0;i<el.getList().size();i++){
                ElemwntList ele1 = new ElemwntList();
                for (int j = 0;j<el.getList().size();j++){
                    if (i == j){
                        ele1.getList().add(el.getList().get(relatedList.get(0)));
                    }
                    if (j == relatedList.get(0)){
                        ele1.getList().add(el.getList().get(i));
                    }
                    ele1.getList().add(el.getList().get(j));
                }
                elementListList.add(ele1);
            }
        }else{
            System.out.println("not belong to SingleItem");
        }
    }

    public void IndexValuePair(int num){
        TestInput ti = new TestInput();
        el = ti.createTestCase_Single("ProgramTest/src/main/java/TestCase/case"+num+".txt", "String", "String");
        if (relatedList.size()+changedList.size()==el.getList().size()){
            for (int m = 0;m<el.getList().size();m++){
                Queue<Element> relatedStack = new LinkedList<>();
                Queue<Element> changedStack = new LinkedList<>();
                for (int i = 0;i<relatedList.size();i++){
                    relatedStack.offer(el.getList().get(relatedList.get(i)));
                }
                for (int j = 0;j<changedList.size();j++){
                    changedStack.offer(el.getList().get(changedList.get(j)));
                }
                int related = relatedList.size();
                int changed = 0;
                ElemwntList ele1 = new ElemwntList();
                if (m<changedList.size()){
                    related = related - m;
                    for (int i1 = 0;i1<related;i1++){
                        ele1.getList().add(relatedStack.remove());
                    }
                    for (int j1 = 0;j1<m;j1++){
                        if (!changedStack.isEmpty()){
                            ele1.getList().add(changedStack.remove());
                        }
                        ele1.getList().add(relatedStack.remove());
                    }
                    while (!relatedStack.isEmpty()){
                        ele1.getList().add(relatedStack.remove());
                    }
                }else {
                    changed = el.getList().size()-m;
                    for (int i2 = 0;i2<changed;i2++){
                        ele1.getList().add(changedStack.remove());
                    }
                    for (int j2 = 0;j2<changedList.size()-changed;j2++){
                        if (!relatedStack.isEmpty()){
                            ele1.getList().add(relatedStack.remove());
                        }
                        ele1.getList().add(changedStack.remove());
                    }
                    while (!changedStack.isEmpty()){
                        ele1.getList().add(changedStack.remove());
                    }
                }
                elementListList.add(ele1);
            }
        }
    }

    public void StrConcat(int num){
        TestInput ti = new TestInput();
        el = ti.createTestCase_Single("ProgramTest/src/main/java/TestCase/case"+num+".txt", "String", "String");
        ElemwntList ele1 = new ElemwntList();
        ele1.getList().add(el.getList().get(el.getList().size()-1));
        for (int i = 0;i<el.getList().size()-1;i++){
            ele1.getList().add(el.getList().get(i));
        }
        elementListList.add(ele1);
    }

    public void MaxRow(int num){
        TestInput ti = new TestInput();
        el = ti.createTestCase_Single("ProgramTest/src/main/java/TestCase/case"+num+".txt", "String", "String");
        if (relatedList.size()==1){

        }
    }

    public void FirstN(int num){
        TestInput ti = new TestInput();
        List<intList> list = new ArrayList<>();
        el = ti.createTestCase_Single("ProgramTest/src/main/java/TestCase/case"+num+".txt", "String", "String");
        if (relatedList.size()!=0&&changedList.size()==0){
            int i = el.getList().size();
            int[] allList = new int[i];
            for (int j = 0;i<i;j++){
                allList[j] = j;
            }
            f(allList,relatedList.size(),0,0,list);
            for (int m = 0;m<list.size();m++){
                ElemwntList ele1 = new ElemwntList();
                int cha = 0;
                int yuan = 0;
                List<Integer> outList = new ArrayList<>();
                for (int c = 0;c<el.getList().size();c++){
                    if (!list.get(m).list.contains(new Integer(c))){
                        outList.add(c);
                    }
                }
                for (int n = 0;n<el.getList().size();n++){
                    if (!relatedList.contains(n)){
                        ele1.getList().add(el.getList().get(outList.get(yuan)));
                        yuan++;
                    }else {
                        ele1.getList().add(el.getList().get(list.get(m).list.get(yuan)));
                    }

                }
                elementListList.add(ele1);
            }
        }
    }

    private void f(int[] shu, int targ, int has, int cur, List<intList> list) {
        if(has == targ) {
            intList il = new intList();
            while (!stack.empty()){
                il.list.add(stack.pop());
            }
            list.add(il);
            System.out.println(stack);
            return;
        }

        for(int i=cur;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                f(shu, targ, has+1, i,list);
                stack.pop();
            }
        }

    }
    public class intList{
        public List<Integer> list = new ArrayList<>();
    }
}

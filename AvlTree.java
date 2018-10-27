import javafx.geometry.Pos;

class Noode{
    Position PositionEntry;
    String word;
    int height;
    Noode left;
    Noode right;
    Noode parent;
}
class returning{
    Noode h;
    boolean bool;
    }
    public class AvlTree {
        Noode root=null;

        public returning find(Position p){
            Noode n=new Noode();
            Noode prev=new Noode();
            n=root;
            int flag=0;
            returning nn=new returning();
            while(n!=null ){
                if(n.PositionEntry.getWordIndex()==p.getWordIndex()){
                    flag=1;
                    break;
                }
                if(n.PositionEntry.getWordIndex()>p.getWordIndex()){
                    prev=n;
                    n=n.left;
                }
                else{
                    prev=n;
                    n=n.right;
                }
            }
            if(flag==1){
                nn.h=n;
                nn.bool=true;
            }
            else{
                nn.bool=false;
                nn.h=prev;
            }
            return nn;
        }
        public boolean checker(Noode i,Noode j){
            int a,b;
            boolean answer;
            if(i==null){
                a=-1;
            }
            else{
                a=i.height;
            }
            if(j==null){
                b=-1;
            }
            else{
                b=j.height;
            }
            if(a>=b){
                if((a-b)<2){
                    answer=true;
                }
                else{
                    answer=false;
                }
            }
            else{
                if((b-a)<2){
                    answer=true;
                }
                else{
                    answer=false;
                }
            }
            return answer;
        }
        public int max(Noode c,Noode d){
            int j,n;
            if(c==null){
                j=-1;
            }
            else{
                j=c.height;
            }
            if(d==null){
                n=-1;
            }
            else{
                n=d.height;
            }
            if(j>=n){
                return j;
            }
            return n;
        }
        public Noode settle_imbalance(Noode n){
            Noode x=new Noode();
            Noode z_subtree=new Noode();
            Noode y_subtree=new Noode();
            Noode x_subtree1=new Noode();
            Noode x_subtree2=new Noode();
            if(n.right==null){
                x=n.left;
            }
            else if(n.left==null){
                x=n.right;
            }
            else if(n.left.height>n.right.height){
                x=n.left;
            }
            else if(n.left.height<=n.right.height){
                x=n.right;
            }
            Noode y=new Noode();
            y=n;
            Noode z=new Noode();
            z=n.parent;
            Noode ne=new Noode();
            ne=n.parent;
            if(z.PositionEntry.getWordIndex()<y.PositionEntry.getWordIndex()&& y.PositionEntry.getWordIndex()<x.PositionEntry.getWordIndex()){
                z_subtree=z.left;
                y_subtree=y.left;
                if(z.parent==null){
                    root=y;
                }
                else if(n.parent.parent.right==z){
                    n.parent.parent.right=y;
                }
                else if(n.parent.parent.left==z){
                    n.parent.parent.left=y;
                }
                y.left=z;
                y.right=x;
                z.left=z_subtree;
                z.right=y_subtree;
                y.parent=z.parent;
                if(y_subtree!=null) {
                    y_subtree.parent = z;
                }
                z.parent=y;
                z.height=max(z.left,z.right)+1;
                x.height=max(x.left,x.right)+1;
                y.height=max(x,z)+1;
                ne=y;
                return y;
            }
            else if(y.PositionEntry.getWordIndex()<z.PositionEntry.getWordIndex()&& x.PositionEntry.getWordIndex()<y.PositionEntry.getWordIndex()){
                z_subtree=z.right;
                y_subtree=y.right;
                if(n.parent.parent==null){
                    root=y;
                }
                else if(n.parent.parent.right==z){
                    n.parent.parent.right=y;
                }
                else if(n.parent.parent.left==z){
                    n.parent.parent.left=y;
                }
                y.left=x;
                y.right=z;
                z.right=z_subtree;
                z.left=y_subtree;
                if(y_subtree!=null) {
                    y_subtree.parent = z;
                }
                y.parent=z.parent;
                z.parent=y;
                z.height=max(z.left,z.right)+1;
                x.height=max(x.left,x.right)+1;
                y.height=max(x,z)+1;
                ne=y;
                return y;
            }
            else if(z.PositionEntry.getWordIndex()<y.PositionEntry.getWordIndex() && y.PositionEntry.getWordIndex()>x.PositionEntry.getWordIndex()){
                z_subtree=z.left;
                y_subtree=y.right;
                x_subtree1=x.left;
                x_subtree2=x.right;
                if(n.parent.parent==null){
                    root=x;
                }
                else if(n.parent.parent.right==z){
                    n.parent.parent.right=x;
                }
                else if(n.parent.parent.left==z){
                    n.parent.parent.left=x;
                }
                x.left=z;
                x.right=y;
                y.right=y_subtree;
                y.left=x_subtree2;
                z.left=z_subtree;
                z.right=x_subtree1;
                x.parent=z.parent;
                z.parent=x;
                y.parent=x;
                if(x_subtree1!=null) {
                    x_subtree1.parent = z;
                }
                if(x_subtree2!=null) {
                    x_subtree2.parent = y;
                }
                z.height=max(z.left,z.right)+1;
                y.height=max(y.left,y.right)+1;
                x.height=max(y,z)+1;
                ne=x;
                return x;
            }
            else if(z.PositionEntry.getWordIndex()>y.PositionEntry.getWordIndex() && x.PositionEntry.getWordIndex()>y.PositionEntry.getWordIndex()){
                z_subtree=z.right;
                y_subtree=y.left;
                x_subtree1=x.left;
                x_subtree2=x.right;
                if(z.parent==null){
                    root=x;
                }
                else if(n.parent.parent.right==z){
                    n.parent.parent.right=x;
                }
                else if(n.parent.parent.left==z){
                    n.parent.parent.left=x;
                }
                x.left=y;
                x.right=z;
                y.left=y_subtree;
                y.right=x_subtree1;
                z.right=z_subtree;
                z.left=x_subtree2;
                x.parent=z.parent;
                y.parent=x;
                z.parent=x;
                if(x_subtree1!=null) {
                    x_subtree1.parent = y;
                }
                if(x_subtree2!=null) {
                    x_subtree2.parent = z;
                }
                z.height=max(z.left,z.right)+1;
                y.height=max(y.left,y.right)+1;
                x.height=max(y,z)+1;
                ne=x;
                return x;
            }
            return ne;
        }
        public void heightupdate(Noode n){
            while(n!=root ){
                if(checker(n.parent.left,n.parent.right)){
                    n.parent.height=max(n.parent.left,n.parent.right)+1;
                    n=n.parent;
                }
                else{
                    Noode ne=settle_imbalance(n);
                    n=ne;
                }
            }
        }
        public void show(){
            Noode sh=new Noode();
            sh=root;
            showing(sh);
        }
        public void showing(Noode r){
            if(r!=null){
                System.out.println(r.PositionEntry.getWordIndex());
                showing(r.left);
                showing(r.right);
            }
            else{
                return;
            }
        }
        public void insert(Position p,String s){
            Noode new_node=new Noode();
            new_node.PositionEntry=p;
            new_node.word=s;
            if(root==null){
                new_node.parent=null;
                new_node.left=null;
                new_node.right=null;
                new_node.height=0;
                root=new_node;
            }
            else{
                returning m=new returning();
                m=find(p);
                Noode b=new Noode();
                b=m.h;
                if(m.bool){
                    while(b.left!=null){
                        b=b.left;
                    }
                    b.left=new_node;
                    new_node.parent=b;
                    new_node.left=null;
                    new_node.right=null;
                    new_node.height=0;
                    heightupdate(new_node);
                }
                else{
                    if(p.getWordIndex()<b.PositionEntry.getWordIndex()){
                        b.left=new_node;
                        new_node.parent=b;
                        new_node.left=null;
                        new_node.right=null;
                        new_node.height=0;
                        heightupdate(new_node);
                    }
                    else{
                        b.right=new_node;
                        new_node.parent=m.h;
                        new_node.left=null;
                        new_node.right=null;
                        new_node.height=0;
                        heightupdate(new_node);
                    }
                }
            }
        }
        public Noode successor (Noode p)
        {
            Noode ans=null;
            returning a=find(p.PositionEntry);
            if (a.bool)
            {
                if  (a.h.right!=null)
                {
                    Noode n=a.h.right;
                    while (n.left!=null)
                    {
                        n=n.left;
                    }
                    ans=n;
                }
                else
                {
                    Noode n1=a.h;
                    Noode n2=a.h.parent;
                    while (n2!=null && n1==n2.right)
                    {
                        n1=n2;
                        n2=n2.parent;
                    }
                    ans=n2;
                }
            }
            return ans;
        }
}
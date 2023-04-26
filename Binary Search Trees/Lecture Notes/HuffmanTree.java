import java.io.PrintStream;
import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree {
    
    // Class to keep weight and symbol.
    public static class HuffData implements Serializable {
        private double weight;
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }

    private BinaryTree<HuffData> huffTree;

    public void buildTree(HuffData[] symbols) {
        // PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
        Queue<BinaryTree<HuffData>> queue = new PriorityQueue<>(symbols.length,
        (lt, rt) -> Double.compare(lt.getData().weight, rt.getData().weight));
    
        // Initialize queue with the leaves.
        for (HuffData symbol : symbols) {
            BinaryTree<HuffData> newNode = new BinaryTree<HuffmanTree.HuffData>(symbol, null, null);
            queue.offer(newNode);
        }              

        // Build the tree.
        while (queue.size() > 1) {
            BinaryTree<HuffData> l = queue.poll();
            BinaryTree<HuffData> r = queue.poll();
            double wl = l.getData().weight;
            double wr = r.getData().weight;
            HuffData sum = new HuffData(wl+wr, '\0');
            BinaryTree<HuffData> newTree = new BinaryTree<>(sum, l, r);
            queue.offer(newTree);
        }
        huffTree = queue.poll();
    }

    /**
     * Prints the encoding message.
     * @param out The output stream.
     */
    public void printCode(PrintStream out) {
        printCode(out, "", huffTree);
    }

    private void printCode(PrintStream out, String code, BinaryTree<HuffData> tree) {
        HuffData data = tree.getData();
        if (data.symbol != '\0') {
            if (data.symbol.equals(" ")) {
                out.println("space: " + code);
            }
            else {
                out.println(data.symbol + ":" + code);
            }
        }
        else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * 
     * @param codedMessage The code that consisting only 0 and 1s.
     * @return Decoded message.
     */
    public String decode(String codedMessage) {
        StringBuilder res = new StringBuilder();
        BinaryTree<HuffData> curTree = huffTree;
        for (int i = 0; i < codedMessage.length(); ++i) {
            if (codedMessage.charAt(i) == '1') {
                curTree = curTree.getRightSubtree();
            }
            else {
                curTree = curTree.getLeftSubtree();
            }
            if (curTree.isLeaf()) {
                Character symbol = curTree.getData().symbol;
                res.append(symbol);
                curTree = huffTree;
            }
        }
        return res.toString();
    }

}

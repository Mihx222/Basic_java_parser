import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class NodeTools {
    /**
     * Method sorts any NodeList by provided attribute.
     *
     * @param nl      NodeList to sort
     * @param tagName tag name to use
     * @param asc     true - ascending, false - descending
     * @param B       class must implement Comparable and have Constructor(String) - e.g. Integer.class , BigDecimal.class etc
     * @return Array of Nodes in required order
     */
    static Node[] sortNodes(NodeList nl, String tagName, boolean asc, Class<? extends Comparable> B) {
        class NodeComparator<T> implements Comparator<T> {
            @Override
            public int compare(T a, T b) {
                int ret;
                Comparable bda = null, bdb = null;
                try {
                    Constructor bc = B.getDeclaredConstructor(String.class);
                    bda = (Comparable) bc.newInstance(((Element) a).getElementsByTagName(tagName).item(0).getTextContent());
                    bdb = (Comparable) bc.newInstance(((Element) b).getElementsByTagName(tagName).item(0).getTextContent());
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
                ret = bda.compareTo(bdb);
                return asc ? ret : -ret;
            }
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            nodes.add(nl.item(i));
        }
        Node[] sortedNodes = new Node[nodes.size()];
        sortedNodes = nodes.toArray(sortedNodes);
        Arrays.sort(sortedNodes, new NodeComparator<>());
        return sortedNodes;
    }
}

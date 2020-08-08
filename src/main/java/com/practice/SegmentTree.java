package com.practice;

public class SegmentTree {

    public int[] createSegmentTree(int input[]) {
        int x = (int) (Math.ceil(Math.log(input.length) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        int segmentTree[] = new int[max_size];
        for (int i = 0; i < segmentTree.length; i++) {
            segmentTree[i] = Integer.MAX_VALUE;
        }
        constructMinSegmentTree(segmentTree, input, 0, input.length - 1, 0);
        return segmentTree;

    }

    public void updateSegmentTree(int input[], int segmentTree[], int index, int delta) {
        input[index] += delta;
        updateSegmentTree(segmentTree, index, delta, 0, input.length - 1, 0);
    }

    public void updateSegmentTreeRange(int input[], int segmentTree[], int startRange, int endRange, int delta) {
        for (int i = startRange; i <= endRange; i++) {
            input[i] += delta;
        }
        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, 0, input.length - 1, 0);
    }

    public int rangeMinimumQuery(int[] segmentTree, int qlow, int qhigh, int len) {
        return rangeMinimumQuery(segmentTree, 0, len - 1, qlow, qhigh, 0);
    }

    public void updateSegmentTreeRangeLazy(int input[], int segmentTree[], int lazy[], int startRange, int endRange, int delta) {
        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange, delta, 0, input.length - 1, 0);
    }

    public int rangeMinimumQueryLazy(int segmentTree[], int lazy[], int qlow, int qhigh, int len) {
        return rangeMinimumQueryLazy(segmentTree, lazy, qlow, qhigh, 0, len - 1, 0);
    }

    private void constructMinSegmentTree(int segmentTree[], int input[], int low, int high, int pos) {
        if (low == high) {
            segmentTree[pos] = input[low];
            return;
        }
        int mid = (low + high) / 2;
        constructMinSegmentTree(segmentTree, input, low, mid, 2 * pos + 1);
        constructMinSegmentTree(segmentTree, input, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private void updateSegmentTree(int segmentTree[], int index, int delta, int low, int high, int pos) {
        if (index < low || index > high) {
            return;
        }
        if (low == high) {
            segmentTree[pos] += delta;
            return;
        }
        int mid = (low + high) / 2;
        updateSegmentTree(segmentTree, index, delta, low, mid, 2 * pos + 1);
        updateSegmentTree(segmentTree, index, delta, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private void updateSegmentTreeRange(int segmentTree[], int startRange, int endRange, int delta, int low, int high, int pos) {
        if (low > high || startRange > high || endRange < low) {
            return;
        }

        if (low == high) {
            segmentTree[pos] += delta;
            return;
        }

        int middle = (low + high) / 2;
        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, low, middle, 2 * pos + 1);
        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, middle + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private int rangeMinimumQuery(int segmentTree[], int low, int high, int qlow, int qhigh, int pos) {
        if (qlow <= low && qhigh >= high) {
            return segmentTree[pos];
        }
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }
        int mid = (low + high) / 2;
        return Math.min(rangeMinimumQuery(segmentTree, low, mid, qlow, qhigh, 2 * pos + 1),
                rangeMinimumQuery(segmentTree, mid + 1, high, qlow, qhigh, 2 * pos + 2));
    }

    private void updateSegmentTreeRangeLazy(int segmentTree[],
                                            int lazy[], int startRange, int endRange,
                                            int delta, int low, int high, int pos) {
        if (low > high) {
            return;
        }
        if (lazy[pos] != 0) {
            segmentTree[pos] += lazy[pos];
            if (low != high) { //not a leaf node
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }
        if (startRange > high || endRange < low) {
            return;
        }
        if (startRange <= low && endRange >= high) {
            segmentTree[pos] += delta;
            if (low != high) {
                lazy[2 * pos + 1] += delta;
                lazy[2 * pos + 2] += delta;
            }
            return;
        }
        int mid = (low + high) / 2;
        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange,
                delta, low, mid, 2 * pos + 1);
        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange,
                delta, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private int rangeMinimumQueryLazy(int segmentTree[], int lazy[], int qlow, int qhigh,
                                      int low, int high, int pos) {

        if (low > high) {
            return Integer.MAX_VALUE;
        }
        if (lazy[pos] != 0) {
            segmentTree[pos] += lazy[pos];
            if (low != high) { //not a leaf node
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }
        if (qlow <= low && qhigh >= high) {
            return segmentTree[pos];
        }
        int mid = (low + high) / 2;
        return Math.min(rangeMinimumQueryLazy(segmentTree, lazy, qlow, qhigh,
                low, mid, 2 * pos + 1),
                rangeMinimumQueryLazy(segmentTree, lazy, qlow, qhigh,
                        mid + 1, high, 2 * pos + 2));

    }

    public static void main(String args[]) {
        SegmentTree st = new SegmentTree();

        int input[] = {0, 3, 4, 2, 1, 6, -1};
        int segTree[] = st.createSegmentTree(input);
        assert 0 == st.rangeMinimumQuery(segTree, 0, 3, input.length);
        assert 1 == st.rangeMinimumQuery(segTree, 1, 5, input.length);
        assert -1 == st.rangeMinimumQuery(segTree, 1, 6, input.length);
        st.updateSegmentTree(input, segTree, 2, 1);
        assert 2 == st.rangeMinimumQuery(segTree, 1, 3, input.length);
        st.updateSegmentTreeRange(input, segTree, 3, 5, -2);
        assert -1 == st.rangeMinimumQuery(segTree, 5, 6, input.length);
        assert 0 == st.rangeMinimumQuery(segTree, 0, 3, input.length);
        int input1[] = {-1, 2, 4, 1, 7, 1, 3, 2};
        int segTree1[] = st.createSegmentTree(input1);
        int lazy1[] = new int[segTree.length];
        st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, 0, 3, 1);
        st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, 0, 0, 2);
        assert 1 == st.rangeMinimumQueryLazy(segTree1, lazy1, 3, 5, input1.length);
    }
}
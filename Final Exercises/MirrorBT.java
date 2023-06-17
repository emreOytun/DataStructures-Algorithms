/*
 * Question: Write a method that takes a BinaryTree as a parameter. Your method returns a new mirror of the parameter.
 *
 */

import lecturenotes.BinaryTree;

public class MirrorBT {
    public static <E> BinaryTree<E> mirrorBT(BinaryTree<E> tree) {
        if (tree == null || tree.isLeaf()) return tree;
        BinaryTree<E> leftTree = mirrorBT(tree.getLeftSubtree());
        BinaryTree<E> rightTree = mirrorBT(tree.getRightSubtree());
        return new BinaryTree<E>(tree.getData(), rightTree, leftTree);
    }
 }
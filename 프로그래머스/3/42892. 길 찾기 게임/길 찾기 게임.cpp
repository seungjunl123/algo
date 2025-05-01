#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

struct Node
{
    int X;
    int Y;
    int Value;
    Node* left;
    Node* right;
};

bool cmp(Node A, Node B)
{
    if(A.Y == B.Y)
    {
        return A.X < B.X;
    }
    return A.Y > B.Y;
    
}

void addNode(Node * parent, Node * child)
{
    if(child->X < parent->X)
    {
        if(parent->left == NULL)
            parent->left = child;
        else
            addNode(parent->left, child);
    }
    else
    {
        if(parent->right == NULL)
            parent->right = child;
        else
            addNode(parent->right, child);
    }
}

void preorder(vector<int> &answer, Node * node)
{
    if(node == NULL)
        return;
    answer.push_back(node->Value);
    preorder(answer, node->left);
    preorder(answer, node->right);
}

void postorder(vector<int> &answer, Node * node)
{
    if(node == NULL)
        return;
    postorder(answer, node->left);
    postorder(answer, node->right);
    answer.push_back(node->Value);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> answer= {{}, {}};
    vector<Node> nodes;
    Node * root;
    for(int i=0;i<nodeinfo.size();i++)
    {
        Node tmp;
        tmp.X = nodeinfo[i][0];
        tmp.Y = nodeinfo[i][1];
        tmp.Value = i + 1;
        nodes.push_back(tmp);
    }
    
    sort(nodes.begin(), nodes.end(), cmp);
    
    root = &nodes[0];
    for(int i=1;i<nodes.size();i++)
    {
        addNode(root, &nodes[i]);
    }
        preorder(answer[0], root);
    postorder(answer[1], root);
    
    
    return answer;
}
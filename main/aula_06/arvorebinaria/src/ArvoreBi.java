public class ArvoreBi<T extends Comparable<T>> {
    private Node<T> raiz;
    private int ultimaBusca = 0;

    public ArvoreBi() {
        raiz = null;
    }

    public void inserir(T info) {
        Node<T> novo = new Node<T>(info);
        if (raiz == null) {
            raiz = novo;
            return;
        }
        Node<T> atual = raiz;
        do {

            if (atual.getInfo().compareTo(info) < 0) {
                if (atual.getEsquerda() != null) {
                    atual = atual.getEsquerda();
                } else {
                    atual.setEsquerda(novo);
                    return;
                }
            } else {
                if (atual.getDireita() != null) {
                    atual = atual.getDireita();
                } else {
                    atual.setDireita(novo);
                    return;
                }
            }

        } while (true);

    }

    public String listaInOrder() {
        return listaInOrder(raiz);
    }

    private String listaInOrder(Node<T> node) {
        String aux = "";
        if (node == null) {
            return aux;
        }
        if (node.getEsquerda() != null) {
            aux += listaInOrder(node.getEsquerda());
        }
        if (node != null) {
            aux += node.getInfo().toString();
            aux += listaInOrder(node.getDireita());
        }
        return aux;
    }

    public String listaPosOrder() {
        return listaPosOrder(raiz);
    }

    private String listaPosOrder(Node<T> node) {
        String aux = "";
        if (node == null) {
            return aux;
        }
        if (node.getDireita() != null) {
            aux += listaPosOrder(node.getDireita());
        }
        if (node != null) {
            aux += node.getInfo().toString();
            aux += listaPosOrder(node.getEsquerda());
        }
        return aux;
    }

    public String listaPreOrder() {
        return listaPreOrder(raiz);
    }

    private String listaPreOrder(Node<T> node) {
        String aux = "";
        if (node == null) {
            return aux;
        }
        aux += node.getInfo().toString();
        if (node.getEsquerda() != null) {
            aux += listaPreOrder(node.getEsquerda());
        }
        if (node.getDireita() != null) {
            aux += listaPreOrder(node.getDireita());
        }
        return aux;
    }

    public boolean localizar(T info) {
        this.ultimaBusca = 0;
        boolean localizou = true;
        if (raiz == null) {
            this.ultimaBusca = 1;
            return false;
        }
        Node<T> node = raiz;
        do {

            this.ultimaBusca++;
            if (node == null) {
                localizou = false;
                break;
            }
            if (node.getInfo().compareTo(info) == 0) {
                localizou = true;
                break;
            }
            if (node.getInfo().compareTo(info) > 0) {
                if (node.getEsquerda() != null) {
                    node = node.getEsquerda();
                } else {
                    localizou = false;
                    break;
                }
            } else {
                if (node.getDireita() != null) {
                    node = node.getDireita();
                } else {
                    localizou = false;
                    break;
                }

            }

        } while (node != null);

        return localizou;
    }

    public int getUltimaBusca() {
        return ultimaBusca;
    }

}

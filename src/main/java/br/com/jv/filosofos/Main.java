import br.com.jv.filosofos.FilosofosComImpasse;

static void main(String[] args) throws Exception {
    final int N = 5;
    for (int i = 0; i < N; i++) {
        new Thread(new FilosofosComImpasse(i)).start();
    }
}
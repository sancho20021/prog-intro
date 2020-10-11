package neerc;

import java.io.*;
import java.util.NoSuchElementException;

public class E {
    static int[][] g;
    static int[] depth;
    static boolean[] used;
    static boolean[] hasTeam;
    static int[] p;
    static int n, m;

    interface Checker {
        boolean isValid(int c);
    }

    static class IntChecker implements Checker {

        public boolean isValid(int c) {
            return !Character.isWhitespace(c) && c != -1;
        }
    }

    static final Checker INT_CHECKER = new IntChecker();

    static class BetaScanner {
        private BufferedReader reader;
        private Checker checker;
        private int cache = 0;

        public BetaScanner(InputStream in, Checker checker) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            cache = reader.read();
            this.checker = checker;
        }

        public BetaScanner(InputStream in, String charset, Checker checker) throws IOException {
            try {
                reader = new BufferedReader(new InputStreamReader(in, charset));
                cache = reader.read();
                this.checker = checker;
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public BetaScanner(File f, Checker checker) throws IOException {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            cache = reader.read();
            this.checker = checker;
        }

        public BetaScanner(File f, String charset, Checker checker) throws IOException {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), charset));
                cache = reader.read();
                this.checker = checker;
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public BetaScanner(String source, Checker checker) throws IOException {
            reader = new BufferedReader(new StringReader(source));
            cache = reader.read();
            this.checker = checker;
        }

        public boolean hasInput() {
            return cache != -1;
        }

        public boolean hasNextToken() throws IOException {
            while (hasInput() && !checker.isValid(cache)) {
                cache = reader.read();
            }
            return checker.isValid(cache);
        }

        public String nextToken() throws IOException {
            hasNextToken();
            StringBuilder str = new StringBuilder();
            while (hasInput() && checker.isValid(cache)) {
                str.append((char) cache);
                cache = reader.read();
            }
            return str.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        public String nextLine() throws IOException {
            StringBuilder s = new StringBuilder();
            while (isNotEndOfLine()) {
                s.append((char) cache);
                cache = reader.read();
            }
            if (cache == -1 && s.length() == 0) {
                throw new NoSuchElementException("No line found");
            }
            skipEndOfLine();
            return s.toString();
        }

        public void skipLine() throws IOException {
            isEndOfLine();
            skipEndOfLine();
        }

        public boolean isEndOfLine() throws IOException {
            while (isNotEndOfLine() && !checker.isValid(cache)) {
                cache = reader.read();
            }
            return !hasInput() || cache == '\n' || cache == '\r';
        }

        private boolean isNotEndOfLine() {
            return hasInput() && cache != '\n' && cache != '\r';
        }

        private void skipEndOfLine() throws IOException {
            if (cache == '\r') {
                cache = reader.read();
            }
            if (cache == '\n') {
                cache = reader.read();
            }
        }

        public void close() throws IOException {
            reader.close();
        }
    }

    static void dfs(int v, int currentDepth) {
        used[v] = true;
        int maxDepth = -1;
        for (int to : g[v]) {
            if (!used[to]) {
                dfs(to, currentDepth + 1);
                maxDepth = Math.max(maxDepth, depth[to]);
                p[to] = v;
            }
        }
        if (maxDepth < 0 && !hasTeam[v]) {
            depth[v] = Integer.MIN_VALUE;
        } else {
            depth[v] = currentDepth;
        }
    }

    public static void main(String[] args) {
        int firstCity = -1;
        try {
            BetaScanner scn = new BetaScanner(System.in, INT_CHECKER);
            try {
                n = scn.nextInt();
                m = scn.nextInt();
                hasTeam = new boolean[n];
                g = new int[n][];
                int[] sizes = new int[n];
                int[] routes = new int[2 * n - 2];
                for (int i = 0; i < n - 1; i++) {
                    int a = scn.nextInt() - 1;
                    int b = scn.nextInt() - 1;
                    sizes[a]++;
                    sizes[b]++;
                    routes[2 * i] = a;
                    routes[2 * i + 1] = b;
                }
                for (int i = 0; i < n; i++) {
                    g[i] = new int[sizes[i]];
                }
                sizes = new int[n];
                for (int i = 0; i < routes.length; i += 2) {
                    g[routes[i]][sizes[routes[i]]++] = routes[i + 1];
                    g[routes[i + 1]][sizes[routes[i + 1]]++] = routes[i];
                }
                firstCity = -1;
                for (int i = 0; i < m; i++) {
                    int x = scn.nextInt() - 1;
                    hasTeam[x] = true;
                    firstCity = firstCity == -1 ? x : firstCity;
                }
            } finally {
                scn.close();
            }
        } catch (IOException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }
        used = new boolean[n];
        depth = new int[n];
        p = new int[n];
        dfs(firstCity, 0);
        int maxDepth = 0;
        int maxDepthCity = firstCity;
        for (int i = 0; i < n; i++) {
            if (depth[i] > maxDepth) {
                maxDepth = depth[i];
                maxDepthCity = i;
            }
        }
        int currentDepth = -1;
        int res = -1;
        int midDepth = maxDepth / 2;
        p[firstCity] = -1;
        for (int i = maxDepthCity; currentDepth < midDepth; i = p[i]) {
            currentDepth++;
            res = i;
        }
        used = new boolean[n];
        depth = new int[n];
        dfs(res, 0);
        for (int i = 0; i < n; i++) {
            if (hasTeam[i] && depth[i] != midDepth) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.println(res + 1);
    }
}

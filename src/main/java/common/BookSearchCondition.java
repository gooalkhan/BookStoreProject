package common;

public enum BookSearchCondition {
    isbn{
        @Override
        public String toString() {
            return "ISBN";
        }
    },

    title{
        @Override
        public String toString() {
            return "제목";
        }
    },

    author{
        @Override
        public String toString() {
            return "저자";
        }
    },

    publisher{
        @Override
        public String toString() {
            return "출판사";
        }
    }
}

package common;

public enum MemberSearchCondition {
	
	
    id{
        @Override
        public String toString() {
            return "아이디";
        }
    },

    name{
        @Override
        public String toString() {
            return "이름";
        }
    },

    mobile{
        @Override
        public String toString() {
            return "연락처";
        }
    },

    address{
        @Override
        public String toString() {
            return "주소";
        }
    }
    
}



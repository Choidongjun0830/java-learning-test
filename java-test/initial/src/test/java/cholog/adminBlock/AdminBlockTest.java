package cholog.adminBlock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdminBlockTest {

    @DisplayName("새로 생성한 관리자는 차단 상태가 아님")
    @Test
    void newCreatedAdmin_NonBlock() {
        Admin admin = new Admin();
        Assertions.assertThat(admin.isBlocked()).isFalse();
    }

    @Test
    void block() {
        Admin admin = new Admin();
        admin.block();

        Assertions.assertThat(admin.isBlocked()).isTrue();
    }

    @Test
    void alreadyBlocked() {
        Admin admin = new Admin();
        admin.block();

        Assertions.assertThatThrownBy(admin::block).isInstanceOf(AlreadyBlockedException.class);
    }

    @Test
    void notBlocked_unblock() {
        Admin admin = new Admin();
        Assertions.assertThatThrownBy(admin::unblocked).isInstanceOf(NonBlockedException.class);
    }

    @Test
    void unblock() {
        Admin admin = new Admin();
        admin.block();
        admin.unblocked();

        Assertions.assertThat(admin.isBlocked()).isFalse();
    }

    private static class Admin {
        private boolean blocked = false;

        public boolean isBlocked() {
            return blocked;
        }

        public void block() {
            if(blocked) {
                throw new AlreadyBlockedException();
            }
            blocked = true;
        }

        public void unblocked() {
            if(!blocked){
                throw new NonBlockedException();
            }
            blocked = false;
        }
    }
}

package com.back.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {
    private final WiseSayingFileRepository wiseSayingFileRepository;

    WiseSayingFileRepositoryTest() {
        wiseSayingFileRepository = new WiseSayingFileRepository();
    }

    @Test
    @DisplayName("저장")
    void t1 () {
        WiseSaying wiseSaying = new WiseSaying("니체",  "나를 죽이지 못하는 고통은 나를 성장시킬 뿐이다.");

        wiseSayingFileRepository.save(wiseSaying);

        WiseSaying foundWiseSaying =  wiseSayingFileRepository.findWiseSayingById(1);

        assertThat(
                foundWiseSaying
        ).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("2번째 등록에서는 2번 명언이 생성된다.")
    void t2() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "나폴레옹");
        wiseSayingFileRepository.save(wiseSaying2);

        assertThat(wiseSaying2.getId()).isEqualTo(2);
    }

    @Test
    @DisplayName("명언 삭제")
    void t3() {
        WiseSaying wiseSaying1 = new WiseSaying("꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingFileRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("나의 죽음을 적들에게 알리지 말라.", "나폴레옹");
        wiseSayingFileRepository.save(wiseSaying2);

        wiseSayingFileRepository.delete(wiseSaying2);

        assertThat(wiseSayingFileRepository.findWiseSayingById(2)).isNull();
    }
}

package com.dongoh.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
//JPA엔티티 클래스들이 baseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class)
////BaseTimeEntity클래스에 Auditing 기능 포함
public abstract class BaseTimeEntity {//모든 entity의 상위 클래스가 되어 엔티티들의 createdDate, modifiedDAte를 자동으로 관리

    @CreatedDate//엔티티가 생성되어 저장될 때 시간이 자동으로 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate//조회한 엔티티 값을 변경할 때의 시간이 자동 저장됨
    private LocalDateTime modifiedDate;

}

package com.source.plusutil.utils.entity

import com.github.f4b6a3.ulid.UlidCreator
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@MappedSuperclass
abstract class PrimaryKeyEntity : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid()

    @Transient
    private var _isNew = true

    override fun getId(): UUID = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as PrimaryKeyEntity).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)

    /*
    @PostPersist와 @PostLoad는 JPA의 수명주기 이벤트에 대한 콜백 방법을 정의하는 것으로
    각각 영속화 이후와 영속화한 데이터를 조회한 이후에 함수가 실행되도록 할 수 있습니다.
     */
    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}

//위의 코드는 JPA 엔티티 클래스를 상속받는 클래스들의 공통적인 기능을 구현하기 위한 PrimaryKeyEntity라는 추상 클래스입니다.
// 이 클래스는 @MappedSuperclass 어노테이션을 사용하여 JPA 매핑 정보가 없는 베이스 클래스로 사용됩니다.
// 즉, 이 클래스에는 테이블이 생성되지 않고 다른 클래스들이 이 클래스를 상속받아서 공통 필드와 메서드를 사용할 수 있습니다.
//
//여기서 PrimaryKeyEntity는 Persistable 인터페이스를 구현하고 있으며,
// 이 인터페이스는 Spring Data JPA에서 제공하는 인터페이스입니다.
// Persistable 인터페이스는 JPA 엔티티가 새로운 엔티티인지 아닌지를 판별하는 데 사용됩니다.
//
//PrimaryKeyEntity 클래스의 주요 내용은 다음과 같습니다:
//
//id 필드: UUID 타입의 식별자로, @Id 어노테이션을 통해 JPA의 기본 키(primary key)로 설정되었습니다.
// @Column(columnDefinition = "uuid") 어노테이션을 통해 컬럼의 데이터 타입을 명시적으로 설정했습니다. 이 필드는 UlidCreator.getMonotonicUlid().toUuid()를 통해 ULID를 생성하고 그 값을 초기화합니다.
//
//_isNew 필드: 이 필드는 엔티티가 새로 생성되었는지를 나타내는 불리언 값입니다.
// 기본적으로 true로 초기화되며, 엔티티가 데이터베이스에 저장된 이후에는 @PostPersist 또는 @PostLoad 어노테이션을 통해 false로 변경됩니다.
//
//getId(): Persistable 인터페이스의 메서드로, 엔티티의 식별자 값을 반환합니다.
//
//isNew(): Persistable 인터페이스의 메서드로, 엔티티가 새로운 엔티티인지 아닌지를 판별하는 데 사용됩니다.
//
//equals(), hashCode(): 엔티티의 식별자를 기준으로 객체의 동등성을 판별하는 메서드입니다. equals() 메서드에서는 객체 타입이 다른 경우에는 동등하지 않다고 판별하고, hashCode() 메서드에서는 식별자 값의 해시 코드를 반환합니다.
//
//load(): @PostPersist와 @PostLoad 어노테이션을 통해 엔티티가 저장 또는 로드된 이후에 _isNew 값을 false로 변경하는 메서드입니다.
//
//이렇게 구현된 PrimaryKeyEntity 클래스를 다른 JPA 엔티티 클래스들이 상속받으면, id 필드와 _isNew 필드, 그리고 getId()와 isNew() 메서드를 사용할 수 있습니다. 이를 통해 엔티티들이 공통적인 기능을 재사용하고 중복을 줄일 수 있습니다.


//@Entity
//@Table(uniqueConstraints = [UniqueConstraint(name = "tag_key_value_uk", columnNames = ["`key`", "`value`"])])
//class Tag(
//        key: String,
//        value: String,
//) : PrimaryKeyEntity() {
//    @Column(name = "`key`", nullable = false)
//    var key: String = key
//        protected set
//
//    @Column(name = "`value`", nullable = false)
//    var value: String = value
//        protected set
//}
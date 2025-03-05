package org.gestionale.gestionalesr.mapper.out;

import org.gestionale.gestionalesr.config.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MapStructConfig.class)
public interface es {
//
//    @Mapping(target = "type", source = "source.type", qualifiedByName = "mapType")
//    @Mapping(target = "description", source = "source.orderData.instrumentName")
//    @Mapping(target = "signer", expression = "java(mapNames(source.getSigners()))")
//    @Mapping(target = "orderer", expression = "java(mapNames(source.getOrderers()))")
//    @Mapping(target = "currency", source = "source.orderData.currency")
//    @Mapping(target = "motivationList", expression = "java(mapMotivations())")
//    @Mapping(target = "date", source = "creationTimestamp", qualifiedByName = "formatDate")
//    @Mapping(target = "proposalCode", source = "source.orderData.isinCode")
//    @Mapping(target = "rapport", source = "source.rapportIn")
//    @Mapping(target = "orderId", source = "source.orderId")
//    OperationRevocationSummaryResource apply(OperationCartResource source, Instant creationTimestamp);
//
//    @Named("mapType")
//    default String mapType(List<OperationCartResource.TypeEnum> types) {
//        return types != null && !types.isEmpty() ? types.get(0).name() : null;
//    }
//
//    default String mapNames(List<?> people) {
//        return people != null && !people.isEmpty()
//                ? people.stream().map(p -> p.toString()).collect(Collectors.joining(", "))
//                : null;
//    }
//
//    default List<MotivationRevocationResource> mapMotivations() {
//        return List.of(
//                new MotivationRevocationResource(RevocationMotivation.NOT_ACCEPTED, RevocationMotivation.NOT_ACCEPTED.getDescription()),
//                new MotivationRevocationResource(RevocationMotivation.VOLUNTARY, RevocationMotivation.VOLUNTARY.getDescription()),
//                new MotivationRevocationResource(RevocationMotivation.ISPAD_ERROR, RevocationMotivation.ISPAD_ERROR.getDescription()),
//                new MotivationRevocationResource(RevocationMotivation.GENERIC_ERROR, RevocationMotivation.GENERIC_ERROR.getDescription()),
//                new MotivationRevocationResource(RevocationMotivation.APP_BLOCKED, RevocationMotivation.APP_BLOCKED.getDescription()),
//                new MotivationRevocationResource(RevocationMotivation.NOT_SPECIFIED, RevocationMotivation.NOT_SPECIFIED.getDescription())
//        );
//    }
//
//    @Named("formatDate")
//    default String formatDate(Instant creationTimestamp) {
//        return creationTimestamp != null
//                ? DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault()).format(creationTimestamp)
//                : null;
//    }
}
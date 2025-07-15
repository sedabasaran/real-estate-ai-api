package com.realestate.finder.specification;

import com.realestate.finder.dto.request.PropertyFilterRequest;
import com.realestate.finder.entity.Property;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecification {

	public static Specification<Property> filterBy(PropertyFilterRequest request) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();

			if (request.getCity() != null && !request.getCity().isBlank()) {
				predicate = cb.and(predicate, cb.like(cb.lower(cb.function("unaccent", String.class, root.get("city"))),
						"%" + removeTurkish(request.getCity().toLowerCase()) + "%"));
			}

			if (request.getDistrict() != null && !request.getDistrict().isBlank()) {
				predicate = cb.and(predicate,
						cb.like(cb.lower(cb.function("unaccent", String.class, root.get("district"))),
								"%" + removeTurkish(request.getDistrict().toLowerCase()) + "%"));
			}

			if (request.getMinPrice() != null) {
				predicate = cb.and(predicate, cb.ge(root.get("price"), request.getMinPrice()));
			}

			if (request.getMaxPrice() != null) {
				predicate = cb.and(predicate, cb.le(root.get("price"), request.getMaxPrice()));
			}

			if (request.getMinRoomCount() != null) {
				predicate = cb.and(predicate, cb.ge(root.get("roomCount"), request.getMinRoomCount()));
			}

			if (request.getMaxRoomCount() != null) {
				predicate = cb.and(predicate, cb.le(root.get("roomCount"), request.getMaxRoomCount()));
			}

			if (request.getMinSquareMeters() != null) {
				predicate = cb.and(predicate, cb.ge(root.get("squareMeters"), request.getMinSquareMeters()));
			}

			if (request.getMaxSquareMeters() != null) {
				predicate = cb.and(predicate, cb.le(root.get("squareMeters"), request.getMaxSquareMeters()));
			}

			if (request.getCategoryId() != null) {
				predicate = cb.and(predicate, cb.equal(root.get("category").get("id"), request.getCategoryId()));
			}

			if (request.getListingType() != null) {
				predicate = cb.and(predicate, cb.equal(root.get("listingType"), request.getListingType()));
			}

			return predicate;
		};
	}

	private static String removeTurkish(String input) {
		return input.replace("ç", "c").replace("ğ", "g").replace("ı", "i").replace("ö", "o").replace("ş", "s")
				.replace("ü", "u");
	}
}

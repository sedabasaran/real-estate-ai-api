package com.realestate.finder.specification;

import com.realestate.finder.dto.request.PropertyFilterRequest;
import com.realestate.finder.entity.Property;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecification {

	public static Specification<Property> filterBy(PropertyFilterRequest request) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();

			predicate = applyStringFilter(predicate, cb, root.get("city"), request.getCity());
			predicate = applyStringFilter(predicate, cb, root.get("district"), request.getDistrict());

			if (request.getMinPrice() != null)
				predicate = cb.and(predicate, cb.ge(root.get("price"), request.getMinPrice()));

			if (request.getMaxPrice() != null)
				predicate = cb.and(predicate, cb.le(root.get("price"), request.getMaxPrice()));

			if (request.getMinRoomCount() != null)
				predicate = cb.and(predicate, cb.ge(root.get("roomCount"), request.getMinRoomCount()));

			if (request.getMaxRoomCount() != null)
				predicate = cb.and(predicate, cb.le(root.get("roomCount"), request.getMaxRoomCount()));

			if (request.getMinSquareMeters() != null)
				predicate = cb.and(predicate, cb.ge(root.get("squareMeters"), request.getMinSquareMeters()));

			if (request.getMaxSquareMeters() != null)
				predicate = cb.and(predicate, cb.le(root.get("squareMeters"), request.getMaxSquareMeters()));

			if (request.getCategoryId() != null)
				predicate = cb.and(predicate, cb.equal(root.get("category").get("id"), request.getCategoryId()));

			if (request.getListingType() != null)
				predicate = cb.and(predicate, cb.equal(root.get("listingType"), request.getListingType()));

			return predicate;
		};
	}

	private static Predicate applyStringFilter(Predicate predicate, jakarta.persistence.criteria.CriteriaBuilder cb,
			jakarta.persistence.criteria.Path<String> path, String value) {
		if (value != null && !value.isBlank()) {
			String normalized = removeTurkish(value.toLowerCase());
			return cb.and(predicate,
					cb.like(cb.lower(cb.function("unaccent", String.class, path)), "%" + normalized + "%"));
		}
		return predicate;
	}

	private static String removeTurkish(String input) {
		return input.toLowerCase().replace("ç", "c").replace("ğ", "g").replace("ı", "i").replace("ö", "o")
				.replace("ş", "s").replace("ü", "u");
	}
}

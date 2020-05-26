-- Categories
INSERT INTO public.category(id, name)
	VALUES (1, 'Economy');
INSERT INTO public.category(id, name)
	VALUES (2, 'Compact');
INSERT INTO public.category(id, name)
	VALUES (3, 'Intermediate');
INSERT INTO public.category(id, name)
	VALUES (4, 'Standard');
INSERT INTO public.category(id, name)
	VALUES (5, 'Full size');
INSERT INTO public.category(id, name)
	VALUES (6, 'Luxury');
INSERT INTO public.category(id, name)
	VALUES (7, 'Premium');
INSERT INTO public.category(id, name)
	VALUES (8, 'Sport');
INSERT INTO public.category(id, name)
	VALUES (9, 'Coupe');
INSERT INTO public.category(id, name)
	VALUES (10, 'Passenger van');
INSERT INTO public.category(id, name)
	VALUES (11, 'Pick up');

-- Tags
INSERT INTO public.tag(id, name)
	VALUES (1, 'High fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (2, 'Medium fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (3, 'Low fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (4, 'Sport engine power');
INSERT INTO public.tag(id, name)
	VALUES (5, 'High engine power');
INSERT INTO public.tag(id, name)
	VALUES (6, 'Medium engine power');
INSERT INTO public.tag(id, name)
	VALUES (7, 'Low engine power');
INSERT INTO public.tag(id, name)
	VALUES (8, 'Family car');
INSERT INTO public.tag(id, name)
	VALUES (9, 'Small family car');
INSERT INTO public.tag(id, name)
	VALUES (10, 'Big family car');
INSERT INTO public.tag(id, name)
	VALUES (11, 'Long range');
INSERT INTO public.tag(id, name)
	VALUES (12, 'Medium range');
INSERT INTO public.tag(id, name)
	VALUES (13, 'Short range');
INSERT INTO public.tag(id, name)
	VALUES (14, 'Unlimited mileage');
INSERT INTO public.tag(id, name)
	VALUES (15, 'High mileage price');
INSERT INTO public.tag(id, name)
	VALUES (16, 'Medium mileage price');
INSERT INTO public.tag(id, name)
	VALUES (17, 'Low mileage price');
INSERT INTO public.tag(id, name)
	VALUES (18, 'Automatic');
INSERT INTO public.tag(id, name)
	VALUES (19, 'Manual');

-- Brand
INSERT INTO public.brand(id, name)
	VALUES (1, 'BMW');

-- Car Model
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (1, 'X5', 1);

-- Fuel
INSERT INTO public.fuel(id, name)
	VALUES (1, 'Diesel');

-- Transmission
INSERT INTO public.transmission(id, name)
	VALUES (1, 'Automatic');

-- Vehicle
INSERT INTO public.vehicle(
	id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id)
	VALUES (1, true, 4, 8, 50, 4, 1, 1, 1, 1);


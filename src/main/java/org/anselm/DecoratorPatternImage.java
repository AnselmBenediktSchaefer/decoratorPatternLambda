package org.anselm;

import java.awt.Color;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
class Camera {
	private Function<Color, Color> filter;
	
	public Camera(Function<Color, Color> ...filters) {
		setFilters(filters);
	}
	
	/*
	//problem in the following:
	//color => color is an identity function
	//(theFilters, aFilter) -> theFilters.andThen(aFilter) => we are receiving parameters and handing it overs: method reference
	public void setFilters(Function<Color, Color>... filters) {
		filter = Stream.of(filters)
						//given a collection of values -> I get a single value
						//first parameter is initial value, second value -> lambda (how to combine values together)
						.reduce(color -> color, (theFilters, aFilter) -> theFilters.andThen(aFilter));
	}
	*/
	
	public void setFilters(Function<Color, Color>... filters) {
		filter = Stream.of(filters)
						//given a collection of values -> I get a single value
						//first parameter is initial value, second value -> lambda (how to combine values together)
						.reduce(Function.identity(), Function::andThen);
	}
	
	public Color snap(Color input) {
		return filter.apply(input);
	}
}

public class DecoratorPatternImage {
	public static void printSnap(Camera camera) {
		System.out.println(camera.snap(new Color(125, 125, 125)));
	}
	
	public static void main(String[] args) {
		printSnap(new Camera());
		
		//Connect multiple functions together!
		printSnap(new Camera(Color::brighter, Color::darker));
	}
}

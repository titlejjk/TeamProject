import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import { Arrow } from './arrow'; // 경로는 실제 파일 위치에 맞게 수정

const SlickSlider = ({ items, settings }) => {
  return (
    <Slider {...settings} nextArrow={<Arrow />} prevArrow={<Arrow />}>
      {items.map((item) => (
        <div className="slick-slide">
          {item}
        </div>
      ))}
    </Slider>
  );
};



export default SlickSlider;
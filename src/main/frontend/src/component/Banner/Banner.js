import React from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Banner.css';

function NextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", filter: "opacity(0.5) drop-shadow(0 0 0 #625f5f)", zoom: "2.5" }}
      onClick={onClick}
    />
  );
}

function PrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", filter: "opacity(0.5) drop-shadow(0 0 0 #625f5f)", zoom: "2.5"}}
      onClick={onClick}
    />
  );
}

function Banner() {
    const publicPath = process.env.PUBLIC_URL;

    const settings = {    
      dots: false,
      arrows: false,      
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3000
    };


    return (
        <div className="slider-container">
            <Slider {...settings}>
                <div>
                    <img 
                        className="image"
                        src={`${publicPath}/images/mainImg1.png`} 
                        alt="배너이미지1" 
                    />
                </div>
                <div>
                    <img 
                        className="image"
                        src={`${publicPath}/images/mainImg2.png`} 
                        alt="배너이미지2" 
                    />
                </div>
                <div>
                    <img 
                        className="image"
                        src={`${publicPath}/images/mainImg3.png`} 
                        alt="배너이미지3" 
                    />
                </div>
            </Slider>
        </div>
    )
}

export default Banner;
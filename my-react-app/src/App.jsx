//This is the Home Page
import React, { use, useState, useRef } from "react";
import { Input, Select } from "antd";
import "./App.css";
import { useNavigate } from "react-router-dom";
import { ArrowLeftOutlined, ArrowRightOutlined } from "@ant-design/icons";

const { Search } = Input;
const { Option } = Select;

function App() {
  const navigate = useNavigate();
  const categories = [
    { name: "Make-Up Artist", icon: "/icons/makeUpArtist.png" },
    { name: "Hair Stylist", icon: "/icons/hairStylist.png" },
    { name: "Photographer", icon: "/icons/photographer.png" },
    { name: "Musician", icon: "/icons/singer.png" },
    { name: "Venue Organiser", icon: "/icons/venueOrganiser.png" },
    { name: "Florist", icon: "/icons/florist.png" },
    { name: "Catering", icon: "/icons/catering.png" },
    { name: "Venue Rental", icon: "/icons/venuerental.png" },
    { name: "Bartender", icon: "/icons/bartender.png" },
    { name: "Mobile Bar", icon: "/icons/mobilebar.png" },
    { name: "Barista", icon: "/icons/barista.png" },
    { name: "Coffee", icon: "/icons/coffee.png" },
    { name: "Ice Cream", icon: "/icons/icecreamVan.png" },
    { name: "Carpenter", icon: "/icons/carpenter.png" },
    { name: "Painter", icon: "/icons/painter.png" },
    { name: "Removalist", icon: "/icons/removalist.png" },
    { name: "Plumber", icon: "/icons/plumber.png" },
    { name: "Cleaner", icon: "/icons/cleaning.png" },
  ];

  const allServices = [
    /* Example Data for Popular Services */
    { id: 1, title: "Make-up Artist", rating: 5.0, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 50, location: "Wollongong", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 2, title: "Photographer", rating: 4.3, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 35, location: "Keiraville", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 3, title: "Professional DJ", rating: 4.1, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 48, location: "Austinmer", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 4, title: "Tables and Chairs", rating: 4.0, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 78, location: "Coniston", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 5, title: "Photographer", rating: 4.9, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 67, location: "Wollongong", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 6, title: "Hair Stylist", rating: 4.8, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 58, location: "Dapto", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 7, title: "Singer/Musician", rating: 4.2, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 31, location: "North Wollongong", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 8, title: "Emcee", rating: 4.4, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 47, location: "Thirroul", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 9, title: "Make-up Artist", rating: 4.5, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 61, location: "Corrimal", isPopular: "yes", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 10, title: "Make-up Artist", rating: 3.2, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 48, location: "Warrawong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 11, title: "Photographer", rating: 4.9, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 51, location: "Wollongong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 12, title: "Professional DJ", rating: 3.2, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 38, location: "Corrimal", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 13, title: "Tables and Chairs", rating: 3.9, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 32, location: "Cringila", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 14, title: "Photographer", rating: 4.7, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 67, location: "Wollongong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 15, title: "Hair Stylist", rating: 2.2, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 58, location: "Horsley", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 16, title: "Singer/Musician", rating: 2.9, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 31, location: "Wollongong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 17, title: "Emcee", rating: 3.7, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 47, location: "Warrawong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
    { id: 18, title: "Make-up Artist", rating: 4.8, description: "Professional photography services. Delivering high-quality, impactful imagery.", rate: 61, location: "West Wollongong", isPopular: "no", profilePic: "/profilepic.jpg", coverPhoto: "/cover-photo.jpg" },
  ];

  const popularServices = allServices.filter(
    (service) => service.isPopular === "yes"
  );

  const [searchQuery, setSearchQuery] = useState("");
  const [selectedLocation, setSelectedLocation] = useState(null);
  const [filteredServices, setFilteredServices] = useState(popularServices);
  const [ isGalleryWrapped, setIsGalleryWrapped ] = useState(false);

  const onSearch = (value) => {
    const term = value.trim().toLowerCase();
    setSearchQuery(term);
    filterServices(term, selectedLocation);

    if (!term && !selectedLocation) {
      showAllServices();
      return;
    }

    setIsGalleryWrapped(true);
    filterServices(term, selectedLocation)
    
  };

  const handleLocationChange = (value) => {
    setSelectedLocation(value);
    filterServices(searchQuery, value);

    if (!searchQuery && !value) {
      showAllServices();
      return;
    } 
    
    setIsGalleryWrapped(true);
    filterServices(searchQuery, value);
  };

  const filterServices = (searchTerm, location) => {
    let results = allServices;

    if (searchTerm) {
      results = results.filter((service) =>
        service.title.toLowerCase().includes(searchTerm)
      );
    }

    if (location) {
      results = results.filter(
        (service) => service.location.toLowerCase() === location.toLowerCase()
      );
    } 

    setFilteredServices(results);
  };

  const goToServicePage = (id) => {
    navigate(`/service/${id}`); // Programmatically navigate to the service page
  };

  const showAllServices = () => {
    setFilteredServices(allServices);
    setSearchQuery("");
    setSelectedLocation(null);
    setIsGalleryWrapped(false);
  };

  const scrollRef = useRef(null);
  const highestRatedScrollRef = useRef(null);
  const popularServicesScrollRef = useRef(null);
  const cheapRateServicesScrollRef = useRef(null);

  const scrollLeft = (ref) => {
    if (ref.current) {
      ref.current.scrollLeft -= 200;
    }
  };

  const scrollRight = (ref) => {
    if (ref.current) {
      ref.current.scrollLeft += 200;
    }
  };

  const highestRatedServices = allServices.filter(
    (service) => parseFloat(service.rating) >= 4.7
  );

  const cheapRateServices = allServices.filter(
    (service) => parseFloat(service.rate) <= 50
  );

  return (
    <div className="pageBackgroundColor">
      <div className="container">
        <div className="searchLocationLinks">
          {/* Search Bar */}
          <Search
            className="navBarSearch"
            placeholder="Search services"
            allowClear
            onSearch={onSearch}
            style={{ width: 400 }}
          />

          {/* Location */}
          <Select
            style={{ width: 200 }}
            onChange={handleLocationChange}
            className="locationSelect"
            value={selectedLocation}
            placeholder="Select your location"
          >
            <Option value="all Services">All locations</Option>
            <Option value="wollongong">Wollongong</Option>
            <Option value="keiraville">Keiraville</Option>
            <Option value="gwynneville">Gwynneville</Option>
            <Option value="north Wollongong">North Wollongong</Option>
          </Select>
        </div>

        <div className="categoriesBar">
          <button className="scrollButton left" onClick={() => scrollLeft(scrollRef)}>
            <ArrowLeftOutlined />
          </button>

          <div className="categoryScroll" ref={scrollRef}>
            {categories.map((category, index) => (
              <div key={index} className="categoryCard">
                <img
                  src={category.icon}
                  alt={category.name}
                  className="categoryIcon"
                />
                <p className="categoryLabel">{category.name}</p>
              </div>
            ))}
          </div>

          <button className="scrollButton right" onClick={() => scrollRight(scrollRef)}>
            <ArrowRightOutlined />
          </button>
        </div>
        
        <div className="titleWithArrows">
          <h1 className="title">
          {searchQuery
            ? "Search Results:"
            : selectedLocation
            ? `Services in ${
                selectedLocation.charAt(0).toUpperCase() +
                selectedLocation.slice(1)
              }`
            : filteredServices.length === allServices.length
            ? "All Services"
            : "Popular Services"}
          </h1>
          <div className="arrowControls">
            <button className="arrowBtn" onClick={() => scrollLeft(popularServicesScrollRef)}><ArrowLeftOutlined /></button>
            <button className="arrowBtn" onClick={() => scrollRight(popularServicesScrollRef)}><ArrowRightOutlined /></button>
          </div>
        </div>

        {/* Gallery for Popular Services */}
        <div className={`scrollWrapper ${isGalleryWrapped ? "wrapped" : ""}`} ref={popularServicesScrollRef}>
          <div className="gallery">
            {filteredServices.map((item) => (
              <div 
                key={item.id} 
                className="galleryItem"
                onClick={() => goToServicePage(item.id)}
                style={{ cursor: "pointer" }}  
              >
                {/* Image */}
                <img
                  src={item.coverPhoto}
                  alt="Cover Photo"
                  className="galleryItemCoverPhoto"
                />
                <div className="galleryItemContent">
                  {/* Row 1: Title and Rate */}
                  <div className="galleryItemTopRow"> 
                    <p className="galleryItemTitle">{item.title}</p>
                    <p className="galleryItemRate">${item.rate}/hr</p>
                  </div>
                  
                  {/* Row 2: Ratings and Location */}
                  <div className="galleryItemBottomRow">
                    <p className="galleryItemRatings">
                      Ratings: {item.rating}
                      <img
                        src="/star.png"
                        alt="Star Icon"
                        className="galleryItemRatingsIcon"
                      />
                    </p>
                    <p className="galleryItemLocation">{item.location}</p>
                  </div>
                </div>
              </div>
            ))}

            {filteredServices.length === 0 && searchQuery && (
              <p>No services found matching your search criteria.</p>
            )}
            {filteredServices.length === 0 && selectedLocation && !searchQuery && (
              <p>No services found in the selected location.</p>
            )}
          </div>
        </div>

        {/* Only Show when no filters */}
        {!searchQuery && !selectedLocation && !isGalleryWrapped && (
          <>
            {/* Highest Rated */}          
            {highestRatedServices.length > 0 && (
              <>
                <hr className="solid" />
                <div className="titleWithArrows">
                  <h1 className="title">Highest Rated</h1>
                  <div className="arrowControls">
                    <button className="arrowBtn" onClick={() => scrollLeft(highestRatedScrollRef)}><ArrowLeftOutlined /></button>
                    <button className="arrowBtn" onClick={() => scrollRight(highestRatedScrollRef)}><ArrowRightOutlined /></button>
                  </div>
                </div>
                <div className="scrollWrapper" ref={highestRatedScrollRef}>
                  <div className="gallery">
                    {highestRatedServices.map((item) => (
                      <div 
                        key={item.id} 
                        className="galleryItem"
                        onClick={() => goToServicePage(item.id)}
                        style={{ cursor: "pointer" }}  
                      >
                        <img
                          src={item.coverPhoto}
                          alt="Cover Photo"
                          className="galleryItemCoverPhoto"
                        />
                        <div className="galleryItemContent">
                          <div className="galleryItemTopRow"> 
                            <p className="galleryItemTitle">{item.title}</p>
                            <p className="galleryItemRate">${item.rate}/hr</p>
                          </div>
                          <div className="galleryItemBottomRow">
                            <p className="galleryItemRatings">
                              Ratings: {item.rating}
                              <img
                                src="/star.png"
                                alt="Star Icon"
                                className="galleryItemRatingsIcon"
                              />
                            </p>
                            <p className="galleryItemLocation">{item.location}</p>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </>
            )}

            {/* Cheap Rates */}
            {cheapRateServices.length > 0 && (
              <>
                <hr className="solid" />
                <div className="titleWithArrows">
                  <h1 className="title">Cheap Rates</h1>
                  <div className="arrowControls">
                    <button className="arrowBtn" onClick={() => scrollLeft(cheapRateServicesScrollRef)}><ArrowLeftOutlined /></button>
                    <button className="arrowBtn" onClick={() => scrollRight(cheapRateServicesScrollRef)}><ArrowRightOutlined /></button>
                  </div>
                </div>
                <div className="scrollWrapper" ref={cheapRateServicesScrollRef}>
                  <div className="gallery">
                    {cheapRateServices.map((item) => (
                      <div 
                        key={item.id} 
                        className="galleryItem"
                        onClick={() => goToServicePage(item.id)}
                        style={{ cursor: "pointer" }}  
                      >
                        <img
                          src={item.coverPhoto}
                          alt="Cover Photo"
                          className="galleryItemCoverPhoto"
                        />
                        <div className="galleryItemContent">
                          <div className="galleryItemTopRow"> 
                            <p className="galleryItemTitle">{item.title}</p>
                            <p className="galleryItemRate">${item.rate}/hr</p>
                          </div>
                          <div className="galleryItemBottomRow">
                            <p className="galleryItemRatings">
                              Ratings: {item.rating}
                              <img
                                src="/star.png"
                                alt="Star Icon"
                                className="galleryItemRatingsIcon"
                              />
                            </p>
                            <p className="galleryItemLocation">{item.location}</p>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </>
            )}
          </>
        )}
      </div>
    </div>
  );
}

export default App;

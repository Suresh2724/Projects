<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="com.tap.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Christ the Redeemer - Wonderland Travels</title>
    <!-- Modern CSS Framework -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- AOS Animation Library -->
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <!-- Custom Styles -->
    <style>
        :root {
            --primary-color: #2c3e50;
            --accent-color: #27ae60;
            --text-light: #ecf0f1;
            --text-dark: #2c3e50;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            overflow-x: hidden;
        }

        .hero-section {
            height: 100vh;
            position: relative;
            overflow: hidden;
        }

        .hero-image {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: -1;
        }

        .hero-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: var(--text-light);
        }

        .navbar {
            background: transparent !important;
            transition: 0.3s;
            padding: 1rem 2rem;
        }

        .navbar.scrolled {
            background: var(--primary-color) !important;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .nav-link {
            color: var(--text-light) !important;
            font-weight: 500;
            margin: 0 1rem;
            position: relative;
        }

        .nav-link::after {
            content: '';
            position: absolute;
            bottom: -5px;
            left: 0;
            width: 0;
            height: 2px;
            background: var(--accent-color);
            transition: 0.3s;
        }

        .nav-link:hover::after {
            width: 100%;
        }

        .section-title {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 2rem;
            position: relative;
            padding-bottom: 1rem;
        }

        .section-title::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100px;
            height: 3px;
            background: var(--accent-color);
        }

        .feature-card {
            background: white;
            border-radius: 15px;
            padding: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            transition: 0.3s;
            margin-bottom: 2rem;
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 40px rgba(0,0,0,0.2);
        }

        .gallery-img {
            width: 100%;
            height: 300px;
            object-fit: cover;
            border-radius: 15px;
            cursor: pointer;
            transition: 0.3s;
        }

        .gallery-img:hover {
            transform: scale(1.05);
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }

        .timeline {
            position: relative;
            padding: 2rem 0;
        }

        .timeline::before {
            content: '';
            position: absolute;
            top: 0;
            left: 50%;
            width: 2px;
            height: 100%;
            background: var(--accent-color);
            transform: translateX(-50%);
        }

        .timeline-item {
            margin-bottom: 3rem;
            position: relative;
        }

        .timeline-content {
            background: white;
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
        }

        .booking-section {
            background: linear-gradient(135deg, #2c3e50, #27ae60);
            padding: 5rem 0;
            color: var(--text-light);
            text-align: center;
        }

        .btn-custom {
            background: var(--accent-color);
            color: var(--text-light);
            padding: 1rem 2rem;
            border-radius: 30px;
            border: none;
            font-weight: 600;
            transition: 0.3s;
        }

        .btn-custom:hover {
            background: #219a52;
            transform: translateY(-3px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.2);
        }

        .testimonial-card {
            background: white;
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            margin: 2rem 0;
        }

        .testimonial-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 1rem;
        }

        footer {
            background: var(--primary-color);
            color: var(--text-light);
            padding: 4rem 0 2rem;
        }

        .social-links a {
            color: var(--text-light);
            margin: 0 1rem;
            font-size: 1.5rem;
            transition: 0.3s;
        }

        .social-links a:hover {
            color: var(--accent-color);
            transform: translateY(-3px);
        }

        .modal-content {
            border-radius: 15px;
            overflow: hidden;
        }

        .modal-header {
            background: var(--primary-color);
            color: var(--text-light);
            border: none;
        }

        .modal-body {
            padding: 2rem;
        }

        .fade-up {
            opacity: 0;
            transform: translateY(20px);
            transition: 0.6s;
        }

        .fade-up.active {
            opacity: 1;
            transform: translateY(0);
        }
    </style>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    boolean isLoggedIn = (user != null);
%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand" href="home.jsp">
            <i class="fas fa-globe-americas"></i>
            <span class="ms-2">Wonderland Travels</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#overview">Overview</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#gallery">Gallery</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#history">History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#booking">Book Now</a>
                </li>
                <% if (isLoggedIn) { %>
                    <li class="nav-item">
                        <span class="nav-link">Welcome, <%= user.getName() %>!</span>
                    </li>
                    <li class="nav-item">
                        <a href="LogoutServlet" class="btn btn-custom">Logout</a>
                    </li>
                <% } else { %>
                    <li class="nav-item">
                        <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#loginModal">Login</button>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero-section">
    <img src="https://images.unsplash.com/photo-1516306580123-e6e52b1b7b5f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80" alt="Christ the Redeemer" class="hero-image">
    <div class="hero-overlay">
        <div class="container">
            <h1 class="display-2 mb-4" data-aos="fade-up">Christ the Redeemer</h1>
            <p class="lead mb-4" data-aos="fade-up" data-aos-delay="200">
                Experience the awe-inspiring statue overlooking Rio de Janeiro, Brazil
            </p>
            <a href="#booking" class="btn btn-custom btn-lg" data-aos="fade-up" data-aos-delay="400">
                Start Your Journey
            </a>
        </div>
    </div>
</section>

<!-- Overview Section -->
<section id="overview" class="py-5">
    <div class="container">
        <h2 class="section-title" data-aos="fade-up">Overview</h2>
        <div class="row">
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="200">
                <div class="feature-card">
                    <i class="fas fa-map-marked-alt fa-3x mb-4 text-primary"></i>
                    <h3 class="h4 mb-3">Location</h3>
                    <p>Perched atop Corcovado Mountain in Rio de Janeiro, Brazil, overlooking the city.</p>
                </div>
            </div>
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="400">
                <div class="feature-card">
                    <i class="fas fa-clock fa-3x mb-4 text-primary"></i>
                    <h3 class="h4 mb-3">Best Time to Visit</h3>
                    <p>Visit during the dry season (June to September) for clearer views and fewer crowds.</p>
                </div>
            </div>
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="600">
                <div class="feature-card">
                    <i class="fas fa-ticket-alt fa-3x mb-4 text-primary"></i>
                    <h3 class="h4 mb-3">Admission</h3>
                    <p>Guided tours available daily with options for scenic train rides or van transportation.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Gallery Section -->
<section id="gallery" class="py-5 bg-light">
    <div class="container">
        <h2 class="section-title" data-aos="fade-up">Gallery</h2>
        <div class="row">
            <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="200">
                <img src="https://images.unsplash.com/photo-1516306580123-e6e52b1b7b5f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="gallery-img" alt="Christ the Redeemer statue"
                     data-bs-toggle="modal" data-bs-target="#imageModal1">
            </div>
            <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="400">
                <img src="https://images.unsplash.com/photo-1564659907532-6b5f98c8e70f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="gallery-img" alt="View from Christ the Redeemer"
                     data-bs-toggle="modal" data-bs-target="#imageModal2">
            </div>
            <div class="col-md-4 mb-4" data-aos="fade-up" data-aos-delay="600">
                <img src="https://images.unsplash.com/photo-1518639192441-8fce0a366e2e?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="gallery-img" alt="Christ the Redeemer at sunset"
                     data-bs-toggle="modal" data-bs-target="#imageModal3">
            </div>
        </div>
    </div>
</section>

<!-- History Timeline Section -->
<section id="history" class="py-5">
    <div class="container">
        <h2 class="section-title" data-aos="fade-up">History Timeline</h2>
        <div class="timeline">
            <div class="timeline-item" data-aos="fade-right">
                <div class="timeline-content">
                    <h3>1922</h3>
                    <p>Construction begins on Corcovado Mountain</p>
                </div>
            </div>
            <div class="timeline-item" data-aos="fade-left">
                <div class="timeline-content">
                    <h3>1931</h3>
                    <p>Inauguration of Christ the Redeemer statue</p>
                </div>
            </div>
            <div class="timeline-item" data-aos="fade-right">
                <div class="timeline-content">
                    <h3>2007</h3>
                    <p>Named one of the New Seven Wonders of the World</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Google Maps Section -->
<section class="py-5 bg-light">
    <div class="container">
        <h2 class="section-title" data-aos="fade-up">Location</h2>
        <div class="row">
            <div class="col-md-12" data-aos="fade-up" data-aos-delay="200">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3673.9963685732454!2d-43.21252868503406!3d-22.95191798498757!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x997fd5984aa13f%3A0x9dc984d7019502de!2sChrist%20the%20Redeemer!5e0!3m2!1sen!2sus!4v1625231234567!5m2!1sen!2sus" 
                        width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>
        </div>
    </div>
</section>

<!-- Booking Section -->
<section id="booking" class="booking-section">
    <div class="container">
        <h2 class="section-title text-center text-white" data-aos="fade-up">Book Your Visit</h2>
        <p class="lead mb-5" data-aos="fade-up" data-aos-delay="200">
            Experience the breathtaking views from Christ the Redeemer with our expert guides
        </p>
        <% if (isLoggedIn) { %>
            <a href="booking.jsp?wonderId=2" class="btn btn-custom btn-lg" data-aos="fade-up" data-aos-delay="400">
                Book Your Tour Now
            </a>
        <% } else { %>
            <button class="btn btn-custom btn-lg" data-aos="fade-up" data-aos-delay="400"
                    data-bs-toggle="modal" data-bs-target="#loginModal">
                Login to Book
            </button>
        <% } %>
    </div>
</section>

<!-- Testimonials Section -->
<section class="py-5">
    <div class="container">
        <h2 class="section-title" data-aos="fade-up">Visitor Experiences</h2>
        <div class="row">
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="200">
                <div class="testimonial-card">
                    <img src="https://randomuser.me/api/portraits/women/3.jpg" alt="Maria" class="testimonial-img">
                    <h4>Maria Silva</h4>
                    <p class="text-muted">★★★★★</p>
                    <p>"The view of Rio from the statue is absolutely breathtaking!"</p>
                </div>
            </div>
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="400">
                <div class="testimonial-card">
                    <img src="https://randomuser.me/api/portraits/men/2.jpg" alt="John" class="testimonial-img">
                    <h4>John Davis</h4>
                    <p class="text-muted">★★★★★</p>
                    <p>"Our guide's knowledge of the statue's history was impressive."</p>
                </div>
            </div>
            <div class="col-md-4" data-aos="fade-up" data-aos-delay="600">
                <div class="testimonial-card">
                    <img src="https://randomuser.me/api/portraits/women/4.jpg" alt="Ana" class="testimonial-img">
                    <h4>Ana Rodrigues</h4>
                    <p class="text-muted">★★★★★</p>
                    <p>"A must-see attraction when visiting Rio de Janeiro!"</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-4 mb-4">
                <h4>Wonderland Travels</h4>
                <p>Exploring the world's wonders, one journey at a time.</p>
                <div class="social-links">
                    <a href="#"><i class="fab fa-facebook"></i></a>
                    <a href="#"><i class="fab fa-twitter"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <h4>Quick Links</h4>
                <ul class="list-unstyled">
                    <li><a href="home.jsp">Home</a></li>
                    <li><a href="#overview">Overview</a></li>
                    <li><a href="#gallery">Gallery</a></li>
                    <li><a href="#booking">Book Now</a></li>
                </ul>
            </div>
            <div class="col-md-4 mb-4">
                <h4>Contact Us</h4>
                <ul class="list-unstyled">
                    <li><i class="fas fa-phone me-2"></i> +1 234 567 890</li>
                    <li><i class="fas fa-envelope me-2"></i> info@wonderland.com</li>
                    <li><i class="fas fa-map-marker-alt me-2"></i> Rio de Janeiro, Brazil</li>
                </ul>
            </div>
        </div>
        <hr class="mt-4 mb-4">
        <p class="text-center mb-0">&copy; 2025 Wonderland Travels. All rights reserved.</p>
    </div>
</footer>

<!-- Image Modals -->
<div class="modal fade" id="imageModal1" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body p-0">
                <img src="https://images.unsplash.com/photo-1516306580123-e6e52b1b7b5f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="img-fluid" alt="Christ the Redeemer statue">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="imageModal2" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body p-0">
                <img src="https://images.unsplash.com/photo-1564659907532-6b5f98c8e70f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="img-fluid" alt="View from Christ the Redeemer">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="imageModal3" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body p-0">
                <img src="https://images.unsplash.com/photo-1518639192441-8fce0a366e2e?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80" 
                     class="img-fluid" alt="Christ the Redeemer at sunset">
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script>
    // Initialize AOS
    AOS.init({
        duration: 1000,
        once: true
    });

    // Navbar scroll effect
    window.addEventListener('scroll', function() {
        if (window.scrollY > 50) {
            document.querySelector('.navbar').classList.add('scrolled');
        } else {
            document.querySelector('.navbar').classList.remove('scrolled');
        }
    });

    // Smooth scroll for navigation links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });
</script>

</body>
</html>


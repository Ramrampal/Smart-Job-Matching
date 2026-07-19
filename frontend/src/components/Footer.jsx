import { Link } from "react-router-dom";
import "../styles/Footer.css";

function Footer() {
  return (
    <footer className="footer">

      <div className="footer-container">

        <div className="footer-section">
          <h2>Smart Job Matching</h2>

          <p>
            A modern job portal connecting talented professionals with leading companies.
          </p>
        </div>

        <div className="footer-section">
          <h3>Quick Links</h3>

          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/jobs">Jobs</Link></li>
            <li><Link to="/post-job">Post Job</Link></li>
            <li><Link to="/login">Login</Link></li>
          </ul>
        </div>

        <div className="footer-section">
          <h3>Contact</h3>

          <p>Email: support@smartjobmatching.com</p>
          <p>Phone: +91 9876543210</p>
          <p>Bengaluru, Karnataka</p>
        </div>

      </div>

      <div className="footer-bottom">
        <p>© 2026 Smart Job Matching. All Rights Reserved.</p>
      </div>

    </footer>
  );
}

export default Footer;
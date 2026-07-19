import "../styles/JobCard.css";

function JobCard({ job }) {
  return (
    <div className="job-card">

      <div className="job-header">
        <h3>{job.title}</h3>
        <span className="salary">{job.salary}</span>
      </div>

      <p>
        <strong>Company:</strong> {job.company}
      </p>

      <p>
        <strong>Location:</strong> {job.location}
      </p>

      <p className="description">
        {job.description}
      </p>

      <div className="button-group">
        <button className="details-btn">
          View Details
        </button>

        <button className="apply-btn">
          Apply Now
        </button>
      </div>

    </div>
  );
}

export default JobCard;
CREATE TABLE feedback (
  id SERIAL PRIMARY KEY,
  sentiment VARCHAR(255),
  code VARCHAR(255),
  reason text,
  feedbackOriginal text,
  suggestedAnswer text
)
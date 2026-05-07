resource "aws_security_group" "authentication_service" {
  name        = "authentication-service-sg"
  description = "Security group for AuthenticationService with restricted access to high-risk ports"
  vpc_id      = var.vpc_id

  # Restricted SSH access from an approved admin network only.
  # Do NOT allow 0.0.0.0/0 or ::/0 on port 22 — unrestricted SSH exposes the
  # service to brute-force and exploitation attempts from the public internet.
  ingress {
    description = "SSH from admin network"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [var.admin_cidr]
  }

  # Restricted RDP access from an approved management network only.
  # Do NOT allow 0.0.0.0/0 or ::/0 on port 3389 — unrestricted RDP is a
  # high-value target for ransomware and brute-force attacks.
  ingress {
    description = "RDP from management network"
    from_port   = 3389
    to_port     = 3389
    protocol    = "tcp"
    cidr_blocks = [var.management_cidr]
  }

  # Explicitly restrict all outbound traffic to only what is required.
  # This prevents the service from being used as a pivot point or for
  # exfiltrating data to arbitrary destinations.
  egress {
    description = "HTTPS outbound for authentication flows"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name    = "authentication-service-sg"
    Service = "AuthenticationService"
    Owner   = "platform-team"
  }
}

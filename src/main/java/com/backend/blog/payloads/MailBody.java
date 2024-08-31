package com.backend.blog.payloads;

import lombok.Builder;

@Builder

public record MailBody( String to,String subject,String text) {
}

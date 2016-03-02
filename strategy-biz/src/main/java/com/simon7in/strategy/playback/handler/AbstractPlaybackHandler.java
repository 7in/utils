package com.simon7in.strategy.playback.handler;


import com.simon7in.strategy.playback.PlaybackContext;
import com.simon7in.strategy.process.config.HandlerConfig;
import com.simon7in.strategy.process.handler.AbstractHandler;

public abstract class AbstractPlaybackHandler extends AbstractHandler<PlaybackContext> {
	@Override
    public boolean canHandle(PlaybackContext param) {
        return param != null;
    }

    @Override
    public HandlerConfig<PlaybackContext> getConfig() {
        return null;
    }

}
